package com.ma.home.controller;

import com.ma.home.model.Finvoices;
import com.ma.home.service.DatabaseRreaderWritter;
import com.ma.home.service.XmlPaserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

@Controller
public class UploadingController {
    public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

    private static final Logger logger = LoggerFactory
            .getLogger(UploadingController.class);

    @Autowired
    private DatabaseRreaderWritter databaseRreaderWritter;

    @Autowired
    private XmlPaserService xmlPaserService;
    @RequestMapping("/")
    public String uploading(Model model) {
        File file = new File(uploadingdir);
        model.addAttribute("files", file.listFiles());
        return "uploading";
    }

    @RequestMapping("/index")
    ModelAndView indexContoller(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView uploadingPost(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {
        ModelAndView mav = null;
        for(MultipartFile uploadedFile : uploadingFiles) {
            File file = new File(uploadingdir + uploadedFile.getOriginalFilename());
            uploadedFile.transferTo(file);
            databaseRreaderWritter.setXmlFileName(file.getName());
            if (!uploadedFile.isEmpty()) {
                try {
                   // String xmlFile = databaseRreaderWritter.uploadXmlFile(uploadedFile.getOriginalFilename(), uploadedFile.getBytes());
                    //logger.info("file name: "+xmlFile);
                    File xmlFile = convertMultipartFileToFile(uploadedFile);
                    Finvoices fininvoices = xmlPaserService.parseXmlFile(xmlFile);
                    if(fininvoices != null){
                        logger.info("parsing succesful! Check frist SellerOrganisationUnitNumbere:  "+fininvoices.getFinvoices().get(0).getSellerOrganisationUnitNumber());
                        boolean success = databaseRreaderWritter.writeInDatabase(fininvoices);
                        logger.info("return afer write success = "+success);
                        String fileName = uploadedFile.getOriginalFilename();
                        fileName = fileName.split(Pattern.quote("."))[0];
                        if(success){
                            mav = new ModelAndView("redirect:/buyer/listBuyer/"+fileName);
                        }
                        else{
                            return new ModelAndView("404");
                        }

                    } else {
                        mav = new ModelAndView("404");
                        mav.addObject("message", "Invalid finvoice xml file");
                    }
                } catch (Exception e) {
                    logger.info("You failed to upload  => " + e.getMessage());
                    mav = new ModelAndView("404");
                    mav.addObject("message", "Invalid finvoice xml file");
                }
            }
            return mav;

        }
        return mav;
    }

    public File convertMultipartFileToFile(MultipartFile file)
    {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            return convFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
