package com.dentist.webapp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Apr 15, 20165:22:31 PM
 * @git
 * 
 */

@RestController
@Transactional
@RequestMapping(value = "/doc")
public class DocumentController {
	private static final Logger LOGGER = Logger.getLogger(PatientInfoController.class);

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam(name = "name", defaultValue = "doc") String name, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
			return "redirect:upload";
		}
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
			return "redirect:upload";
		}

		if (!file.isEmpty()) {
			try {
				String currentDirectory = new File("").getAbsolutePath();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(currentDirectory + File.separator + name)));
				FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + name + "!");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message", "You failed to upload " + name + " => " + e.getMessage());
				LOGGER.error(e.getMessage());
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "You failed to upload " + name + " because the file was empty");
		}

		return name;
	}

}
