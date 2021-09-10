import java.io.*;

/**
 * Created by alxbrd on 23/03/18.
 */
public class CrepeResults {

    public static void main(String... args) {
        File[] files = new File("/home/alxbrd/projects/alxbrd/github/experiments-results-henshin/run-2/").listFiles();
        if (files != null)
            getFile(files);
    }

    public static void getFile(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                getFile(file.listFiles());
            } else {

                if(file.getName().endsWith("pf")){
                    flipObjectives(file);
                }

                System.out.println("File: " + file.toString());
            }
        }
    }


    private static void flipObjectives(File file){
        BufferedReader br = null;
        String[] characters = new String[1024];//just an example - you have to initialize it to be big enough to hold all the lines!

        try {

            String sCurrentLine;
            br = new BufferedReader(new FileReader(file));


            PrintWriter out = null;
            try {
                out = new PrintWriter(new OutputStreamWriter(
                        new BufferedOutputStream(new FileOutputStream(file.toString().concat("2"))), "UTF-8"));



                int i=0;
                while ((sCurrentLine = br.readLine()) != null) {
                    String[] arr = sCurrentLine.split(" ");

                    Double obj1 = Double.parseDouble(arr[0]);
                    Double obj2 = Double.parseDouble(arr[1]);
                    Double obj3 = Double.parseDouble(arr[2]);

                    if(obj1 < 0){
                        obj1 = obj1 * -1;
                    }

                    obj2 = obj2 * -1;

                    out.println(String.format("%s %s %s", obj1, obj2, obj3));
                    i++;
                }

                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
