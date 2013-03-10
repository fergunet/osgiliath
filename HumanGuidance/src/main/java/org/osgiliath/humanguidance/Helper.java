/*
 * This code is licensed under GPL v3
 * autor raiben <raiben@gmail.com>
 */
package org.osgiliath.humanguidance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to help the human guidance html view
 *
 * @author rhgarcia
 */
public class Helper {

    private static Helper instance = null;
    private Properties props = null;
    private Random random = null;

    public static Helper getInstance() {
        if (instance == null) {
            instance = new Helper();
        }
        return instance;
    }

    private Helper() {
        props = new Properties();
        try {
            props.load(Helper.class.getClassLoader().getResourceAsStream(
                    "/humanguidance.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }

        random = new Random();
    }

    /**
     * Retrieves a number of image names from the data directory
     *
     * @param number number of images
     * @param ignoreSet a set of images that must be ignored
     * @return an array of string with the name of the images (including the
     * base url)
     */
    public String[] getRandomFileNames(int number, HashSet<String> ignoreSet) {
        File dir = new File(props.getProperty("data.url"));
        File[] files = dir.listFiles();

        ArrayList<String> imageNames = new ArrayList<String>();

        // filter: only images
        for (File f : files) {
            if (f.getName().toLowerCase().matches(".*(\\.png|\\.jpg|\\.gif)")) {
                imageNames.add(f.getName());
            }
        }
        
        // filter: remove images in ignoreSet
        Iterator<String> it = ignoreSet.iterator();
        while (it.hasNext()){
            String n = it.next();
            if (imageNames.contains(n)){
                imageNames.remove(n);
            }
        }
        
        // filter: retrieve random images from the list
        String[] ret = new String[number];
        HashSet<Integer> numset = new HashSet<Integer>();
        for (int i = 0; i < number; i++) {
            if (imageNames.size()>0){
                int v = random.nextInt(imageNames.size());
                ret[i] = getImageBaseURL()+imageNames.get(v);
                imageNames.remove(v);
            }else{
                ret[i]="noimage";
            }
        }
        
        return ret;
    }

    public String getImageBaseURL() {
        return props.getProperty("data.baseURL");
    }

    /**
     * Votes an image
     *
     * @param name the name of the image (not the full path)
     * @param value the value to add (can also be negative)
     */
    public void vote(String name, float value) {
        File f = new File(props.getProperty("data.url") + name);
        if (f.exists()) {
            Properties fProp = new Properties();
            try {
                fProp.load(new FileInputStream(props.getProperty("data.url")
                        + name + ".properties"));
            } catch (FileNotFoundException ex) {
                fProp.setProperty("votes", "0");
            } catch (IOException ex) {
                fProp.setProperty("votes", "0");
            }

            int votes = Integer.parseInt(fProp.getProperty("votes"));
            votes++;
            fProp.setProperty("votes", Integer.toString(votes));

            try {
                fProp.store(new FileOutputStream(props.getProperty("data.url")
                        + name + ".properties"), "");
            } catch (IOException ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null,
                        ex);
            }
        }
    }
}
