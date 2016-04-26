package com.dentist.webapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Apr 25, 20166:00:49 PM
 * @git
 * 
 */
public class FileUploadDownloadHandler {

	private static final Logger LOGGER = Logger.getLogger(FileUploadDownloadHandler.class);

	public static boolean uploadFile(Map<String, String> map, MultipartFile file, String name, String dir) {
		boolean done = false;
		if (!file.isEmpty()) {

			try {

				String fullname = file.getOriginalFilename();
				if (fullname.matches("[\\/\\\\]")) {
					int index_path_seperator = fullname.lastIndexOf("/");
					if (index_path_seperator == -1) {
						fullname.lastIndexOf("\\");
					}
					fullname = fullname.substring(index_path_seperator + 1);
				}

				if (!fullname.matches("[.]{2}")) {

					int index_ext_seperator = fullname.lastIndexOf(".");
					// String filename = fullname.substring(0,
					// index_ext_seperator - 1).toLowerCase();
					String fileExtension = fullname.substring(index_ext_seperator + 1).toLowerCase();

					// Creating new directory in Java, if it doesn't exists
					if (fileExtension.equals("pdf") || fileExtension.equals("doc") || fileExtension.equals("docx") || fileExtension.equals("csv")) {

						map.put("extension", fileExtension);
						File folder = new File(dir);
						if (!folder.exists()) {
							if (folder.mkdirs()) {
								LOGGER.debug("Directory created");
							} else {
								LOGGER.debug("Directory creation failed");
							}

						}
						String file_name = "File";
						int fileVersion = 1;
						boolean fileCheck = new File(dir, name + "_" + fileVersion).exists();

						if (fileCheck) {
							while (fileCheck) {
								fileVersion++;
								file_name = name + "_" + fileVersion;
								fileCheck = new File(dir, file_name).exists();
							}

						} else {
							file_name = name + "_" + fileVersion;
						}
						String path = dir + File.separator + file_name;
						map.put("path", path);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						FileCopyUtils.copy(file.getInputStream(), stream);
						stream.close();
						done = true;
						/*
						 * File doc = new File(dir, file_name); Path path =
						 * Paths.get(doc.getPath()); InputStream fileStream =
						 * file.getInputStream(); Files.copy(fileStream, path);
						 * done = true;
						 */

					}

				} else {
					map.put("error", "Invalid file name");
				}

			} catch (Exception e) {

				LOGGER.error(e.getMessage());
			}
		} else {
			map.put("error", "The file is empty");
		}

		return done;
	}

	public static void downloadFile(HttpServletResponse response, String path, String extension) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			LOGGER.error(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();

		} else {
			String fileName = file.getName() + "." + extension;
			String mimeType = URLConnection.guessContentTypeFromName(fileName);
			if (mimeType == null) {
				LOGGER.debug("mimetype is not detectable, will take default");
				mimeType = "application/octet-stream";
			}

			LOGGER.debug("mimetype : " + mimeType);

			response.setContentType(mimeType);

			/*
			 * "Content-Disposition : inline" will show viewable types [like
			 * images/text/pdf/anything viewable by browser] right on browser
			 * while others(zip e.g) will be directly downloaded [may provide
			 * save as popup, based on your browser setting.]
			 */
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileName + "\""));

			/*
			 * "Content-Disposition : attachment" will be directly download, may
			 * provide save as popup, based on your browser setting
			 */
			// response.setHeader("Content-Disposition",
			// String.format("attachment;
			// filename=\"%s\"", file.getName()));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			// Copy bytes from source to destination(outputstream in this
			// example),
			// closes both streams.
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}

	}
}
