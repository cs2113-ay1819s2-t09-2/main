package seedu.address.storage;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.XmlUtil;


public class XmlFileStorage {

    /**
     * Saves the given account list data to the specified file.
     */
    public static void saveAccountListDataToFile(Path file, XmlSerializableAccountList accountList)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, accountList);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }



    /**
     * Returns account list in the file or an empty account list
     */
    public static XmlSerializableAccountList loadAccountListDataFromSaveFile(Path file) throws DataConversionException,
            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableAccountList.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }


}
