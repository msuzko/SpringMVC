package com.mec.spring.controller;

import com.mec.spring.objects.UploadedFile;
import com.mec.spring.validators.FileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;

@Controller
@SessionAttributes("filename")
public class FileController {

    public static final String MAIN = "main";
    public static final String FILEUPLOADED = "fileuploaded";
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private FileValidator fileValidator;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView uploadFile(@ModelAttribute("uploadedFile") UploadedFile uploadedFile, BindingResult result) {

        ModelAndView modelAndView = new ModelAndView();

        fileValidator.validate(uploadedFile, result);
        if (result.hasErrors())
            modelAndView.setViewName(MAIN);
        else
            try {
                copyFile(uploadedFile, getTempDir());
                redirectView(uploadedFile, modelAndView);
            } catch (IOException e) {
                modelAndView.setViewName(MAIN);
            }
        return modelAndView;
    }

    private void copyFile(UploadedFile file, File dir) throws IOException {
        byte[] bytes = file.getFile().getBytes();
        File loadFile = new File(dir.getAbsolutePath() + File.separator + file.getFileName());
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(loadFile));
        stream.write(bytes);
        stream.flush();
        stream.close();

        logger.info("uploaded: " + loadFile.getAbsolutePath());
    }

    private File getTempDir() throws FileNotFoundException {
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles");
        if (!dir.exists() && !dir.mkdirs())
            throw new FileNotFoundException();
        return dir;
    }

    private void redirectView(UploadedFile uploadedFile, ModelAndView modelAndView) {
        RedirectView redirectView = new RedirectView(FILEUPLOADED);
        redirectView.setStatusCode(HttpStatus.FOUND);
        modelAndView.setView(redirectView);
        modelAndView.addObject("filename", uploadedFile.getFileName());
    }

    @RequestMapping(value = "/" + FILEUPLOADED, method = RequestMethod.GET)
    public String getFilename() {
        return FILEUPLOADED;
    }


}
