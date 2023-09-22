// Spellathon Solver

//This program will generate words that have all essential characters and zero or more other characters - that you provide as inputs. Word will not have any character apart from essentials and others"
// Importing required classes
import java.io.*;
import java.util.*;

// Class
public class Main {


    // Function to remove duplicate from string
    public static String removeDuplicates(String a)
    {
        char[] ch = a.toCharArray();

        LinkedHashSet<String> set
                = new LinkedHashSet<String>();

        // adding elements to LinkedHashSet
        for (int i = 0; i < ch.length; i++)
            set.add(String.valueOf(ch[i]));

        // Print the elements of LinkedHashSet

        return String.join("", set);
    }

    // Function to check essentials in string
    public static String checkEssentials(String word, String essentials)
    {
        String w = removeDuplicates(word);
        String e = removeDuplicates(essentials);
        //System.out.println("after removing duplicates " + e);

        int ctr = 0;

        // adding elements to LinkedHashSet
        for (int j = 0; j < w.length(); j++) {
            for (int i=0; i < e.length(); i++) {
                if (e.charAt(i) == w.charAt(j)) {
                    ctr=ctr + 1;
                }
            }
        }
        //System.out.print(ctr + " " + e.length());
        if (ctr==e.length())
        {
            return "Yes";
        }
        else
        {
            return "No";
        }

    }

    // Function to check words formed with essentials (all) and others (0 or more) in string
    public static String checkword(String word, String essentials, String others)
    {
        String w = removeDuplicates(word);
        String full = others+essentials;
        String f = removeDuplicates(full);

        String output = checkEssentials(f,w);
        if (output.equals("Yes"))
        {
            //word has all essential chars and zero or more other characters. Word will not have any character apart from essentials and other

            return word;
            //System.out.println(word);
        }
        else
        {
            return "No Match";
        }
    }

    // Main driver method
    public static void main(String args[]) throws IOException {
        // Custom input string
        Scanner myObj=new Scanner(System.in);  // Create a Scanner object
        String success = null;
        String again=null;
        do {


            System.out.println("\n\nWelcome to Spellathon solver!! \n\n");
            System.out.println("This program will generate words that have all essential characters and zero or more other characters - that you provide as inputs.\nWord will not have any character apart from essentials and others\n\n");
            System.out.println("Please enter String with essential characters, eg ca");

            String essentials=myObj.nextLine();  // Read user input


            myObj=new Scanner(System.in);  // Create a Scanner object
            System.out.println("Please enter String with other characters, eg tbe");

            String others=myObj.nextLine();  // Read user input
            //System.out.println("String with other characters is: " + others);

            String word;

            String currentPath=new java.io.File(".").getCanonicalPath();
            System.out.println("Please place the dictionary file (collection of words) at following location: " + currentPath + File.separator + "dictionary.txt" + " Once done press enter.");
            myObj.nextLine();
            FileInputStream fstream=null;
            BufferedReader br=null;
            FileOutputStream fileOutStream=null;

            do {//try till dictionary file is placed


                try {

                    // Open the file
                    fstream=new FileInputStream(currentPath + File.separator + "dictionary.txt");
                    br=new BufferedReader(new InputStreamReader(fstream));
                    success="True";
                } catch (FileNotFoundException e) {
                    System.out.println("Please place the dictionary file (collection of words) at following location:" + currentPath + File.separator + "dictionary.txt" + " Once done press enter.");
                    success = "False";
                    myObj.nextLine();
                }
            }while(success.equals("False"));



            //Declaring reference of File class
            File file=null;
            //Declaring reference of FileOutputStream class

            //String data=word;
            try {
                file=new File(currentPath + File.separator + "output.txt");
                //Creating Object of FileOutputStream class
                fileOutStream=new FileOutputStream(file);
                //In case file does not exists, Create the file
                if (!file.exists()) {
                    file.createNewFile();
                }



            String strLine;
            int ctr=0;
            //Read File Line By Line
            while ((strLine=br.readLine()) != null) {


                word=strLine;

                String allpresent=checkEssentials(word, essentials);

                if (allpresent.equals("Yes")) {


                    String result=checkword(word, essentials, others);
                    if (!result.equals("No Match")) {


                            //fetching the bytes from data String
                            byte[] b=word.getBytes();
                            //Writing to the file
                            fileOutStream.write(b);
                            fileOutStream.write('\n');

                            //Flushing the stream
                            fileOutStream.flush();
                            //Closing the stream

                        System.out.println(word);
                        ctr=ctr + 1;
                    }
                }

            }
            if (ctr == 0) {

                //byte[] b=String.valueOf("");
                //Writing to the file
                fileOutStream.write("No match found".getBytes());
                fileOutStream.write('\n');

                //Flushing the stream
                fileOutStream.flush();


                System.out.println("No match found");
            }


            }
            //Handing Exception
            catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutStream != null) {
                        fileOutStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



//Close the input stream
            System.out.println("\n\nFile (output.txt) writing done at " + currentPath + File.separator + "output.txt\n\n");
            fileOutStream.close();
            fstream.close();

            do
            { // repeat while user doesn't enter y/n as input
                System.out.println("Want to play again (y/n)? Please enter either \"y\" or \"n\" only (case-insensitive)");
                try {
                    //in2=new Scanner(System.in);
                    again = myObj.nextLine();
                } catch(NoSuchElementException e)
                {System.out.println("Exiting the game!");
                    System.exit(0);}
            } while (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("n"));


        }while(again.equalsIgnoreCase("y")); // repeat on y
    }
}
