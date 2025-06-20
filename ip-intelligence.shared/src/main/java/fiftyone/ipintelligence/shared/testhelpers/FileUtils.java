/* *********************************************************************
 * This Original Work is copyright of 51 Degrees Mobile Experts Limited.
 * Copyright 2025 51 Degrees Mobile Experts Limited, Davidson House,
 * Forbury Square, Reading, Berkshire, United Kingdom RG1 3EU.
 *
 * This Original Work is licensed under the European Union Public Licence
 * (EUPL) v.1.2 and is subject to its terms as set out below.
 *
 * If a copy of the EUPL was not distributed with this file, You can obtain
 * one at https://opensource.org/licenses/EUPL-1.2.
 *
 * The 'Compatible Licences' set out in the Appendix to the EUPL (as may be
 * amended by the European Commission) shall be deemed incompatible for
 * the purposes of the Work and the provisions of the compatibility
 * clause in Article 5 of the EUPL shall not apply.
 *
 * If using the Work as, or as part of, a network application, by
 * including the attribution notice(s) required under Article 5 of the EUPL
 * in the end user terms of the application under an appropriate heading,
 * such notice(s) shall fulfill the requirements of that article.
 * ********************************************************************* */

package fiftyone.ipintelligence.shared.testhelpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static fiftyone.pipeline.util.FileFinder.getFilePath;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class FileUtils {
    public static final String ENTERPRISE_IPI_DATA_FILE_NAME = "Enterprise-IpIntelligenceV41.ipi";

    public static final String ENTERPRISE_IPI_V41_DATA_FILE_NAME = "51Degrees-EnterpriseIpiV41.ipi";
    public static final String LITE_IPI_DATA_FILE_NAME = "51Degrees-LiteV41.ipi";
    private static  Optional<File> IPI_DATA_FILE;
    public static final String IP_ADDRESSES_FILE_NAME = "evidence.csv";
    private static File UA_FILE;
    public static final String EVIDENCE_FILE_NAME = "evidence.yml";
    private static Optional<File> EVIDENCE_FILE;

    /**
     * Helper to find the location of an Enterprise or Lite IPI file in the default search scope
     *
     * @return a file or null if not found
     */
    public static String getHashFileName() {
        return Objects.isNull(getHashFile()) ? null : getHashFile().getPath();
    }

    /**
     * Helper to find the location of an Enterprise or Lite IPI file in the default search scope
     *
     * @return a file or empty if not found
     */
    public static File getHashFile() {
        try {
            if (Objects.nonNull(IPI_DATA_FILE)){
                return IPI_DATA_FILE.orElse(null);
            }
            // following throws exception if not found
            IPI_DATA_FILE = Optional.of(getFilePath(ENTERPRISE_IPI_V41_DATA_FILE_NAME));
            return IPI_DATA_FILE.get();
        } catch (Exception e) {
            try {
                // following throws exception if not found
                IPI_DATA_FILE = Optional.of(getFilePath(ENTERPRISE_IPI_DATA_FILE_NAME));
                return IPI_DATA_FILE.get();
            } catch (Exception ex) {
                try {
                    // following throws exception if not found
                    IPI_DATA_FILE = Optional.of(getFilePath(LITE_IPI_DATA_FILE_NAME));
                    return IPI_DATA_FILE.get();
                } catch (Exception exc) {
                    IPI_DATA_FILE = Optional.empty();
                    return null;
                }
            }
        }
    }

    public static File getEvidenceFile() {
        if (Objects.nonNull(EVIDENCE_FILE)) {
            return EVIDENCE_FILE.orElse(null);
        }
        try {
           EVIDENCE_FILE = Optional.of(getFilePath(EVIDENCE_FILE_NAME));
           return EVIDENCE_FILE.get();
        } catch (Exception e) {
            EVIDENCE_FILE = Optional.empty();
            return null;
        }
    }

    /**
     * Prefix for temp files that are created by {@link #jarFileHelper(String)}
     **/
    public static String TEMP_FILE_PREFIX = "DDTempFile";

    /**
     * Search the classpath for a resource. If it doesn't exist throw an exception.
     * If the file is in a jar then copy it to a temp file, so it can be used as an actual file.
     *
     * Callers might wish to delete the temp file created when one is created and
     * can assess whether this is the case as the name of the filename created will
     * start with {@link #TEMP_FILE_PREFIX} which they can alter to suit their needs.
     *
     * Files created here have the property deleteOnExit;
     *
     * @param file a filename
     * @return a File representing the resource
     */
    public static File jarFileHelper(String file) {
        // find the resource in the classpath
        URL resource = Objects.requireNonNull(FileUtils.class.getClassLoader().getResource(file),
                file + " could not be found on the classpath");
        try {
            return new File(resource.toURI());
        } catch (Exception e) {
            if (!resource.getPath().contains(".jar!"))
                throw new IllegalStateException(resource.getPath() + " can't be loaded", e);
        }
        // make a copy of the file if it is in a jar file (assumption is that if the
        // name matches the pattern then it is in a jar, if not, no harm is done
        // but an irrelevant tmp file may be created)
        String filePath;
        try {
            filePath = URLDecoder.decode(resource.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        // we will try to match the extension
        int index = filePath.lastIndexOf(".");
        String extension = index < 0 ? "tmp" : filePath.substring(index);
        try {
            Path temp = Files.createTempFile(TEMP_FILE_PREFIX, extension);
            try (InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(file)) {
                //noinspection ConstantConditions
                Files.copy(is, temp, REPLACE_EXISTING);
            }
            filePath = temp.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        File f = new File(filePath);
        f.deleteOnExit();
        return f;
    }
}