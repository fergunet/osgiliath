/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

    public String[] getRandomFileNames(int number) {
        File dir = new File(props.getProperty("data.url"));
        File[] files = dir.listFiles();

        ArrayList<String> imageNames = new ArrayList<String>();

        for (File f : files) {
            if (f.getName().toLowerCase().matches(".*(\\.png|\\.jpg|\\.gif)")) {
                imageNames.add(f.getName());
            }
        }

        HashSet<Integer> numset = new HashSet<Integer>();
        for (int i = 0; i < number; i++) {
            int v = 0;
            do {
                v = random.nextInt(imageNames.size());
            } while (numset.contains(v));
            numset.add(v);

        }
        String[] ret = new String[number];

        int i = 0;
        Iterator<Integer> it = numset.iterator();
        while (it.hasNext()) {
            ret[i] = getImageBaseURL() + imageNames.get(it.next());
            i++;
        }

        return ret;
    }

    public String getImageBaseURL() {
        return props.getProperty("data.baseURL");
    }

    public void increaseVotes(String name) {
        File f = new File(props.getProperty("data.url") + name);
        //if (f.exists()) {
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
        //}
    }
}
