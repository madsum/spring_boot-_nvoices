package com.ma.home.model;

import com.ma.home.utility.Utility;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

public class PaymentTermsDetails {

    private static final Logger logger = LoggerFactory
            .getLogger(PaymentTermsDetails.class);
    /**
     * For annotation all subsequent properties name must be presented as same as in XML file.
     */
    private InvoiceDueDate invoiceDueDate;
    private String dueDate;

    /**
     * All properties setter and getter
     */
    @XmlElements({ @XmlElement(name = "InvoiceDueDate", type = InvoiceDueDate.class),
            @XmlElement(name = "PaymentTermsDetails", type = PaymentTermsDetails.class) })
    public void setInvoiceDueDate(InvoiceDueDate duedata) {
        invoiceDueDate = duedata;
        logger.info("InvoiceDueDate content: "+duedata.getDate());
        logger.info("InvoiceDueDate format: "+duedata.getFormat());
        // use service class for string formating
        String format = Utility.getDateFormatString(duedata.getFormat());
        String dateVal = Utility.getDateValue(duedata.getDate());
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        DateTime dt = formatter.parseDateTime(dateVal);
        dueDate = dt.toString("dd-MM-yyyy");

    }

    public InvoiceDueDate getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }


}
