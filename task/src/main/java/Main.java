import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*
{
    "title" : "<title>",
    "time" : "<time>",
    "message" : "message"
}
 */

public class Main {

    static private String checkStrings(String s)
    {
        String[] tmp1 = s.split(":");
        if (tmp1[0].length() != 2 )
        {
            tmp1[0] = "0" + tmp1[0];
        }
        if (tmp1[1].length() != 2 )
        {
            tmp1[1] = "0" + tmp1[1];
        }
        s = tmp1[0].concat(":");
        s = s.concat(tmp1[1]);
        return s;
    }
    static public void main(String args[])
    {
        NoteBook nb;
        String name;
        Scanner inputS = new Scanner(System.in);
        System.out.println("do you want to use already created notebook?");

        if (inputS.nextLine().contains("yes"))
        {
            System.out.println("write please name of the file");
            name = inputS.nextLine();
            try
            {
                nb = jsonConverter.loadFromJSON(Path.of(name));
            }
            catch (IOException e)
            {
                System.out.println("There was an error in opening your notebook it will be recreated");
                nb = new NoteBook();
            }
        }
        else
        {
            System.out.println("how would you like to call it ?");
            name = inputS.nextLine();
            nb = new NoteBook();
        }

        Boolean notStopped = true;
        System.out.println("notebook opened");
        while(notStopped)
        {
            String str = inputS.nextLine();
            Scanner s = new Scanner(str);
            int counter = 0 ;
            while(s.hasNext())
            {
                String token = s.next();
                if(counter ==0 && !token.equals("notebook"))
                {
                    System.out.println("wrong input");
                    break;
                }
                if (counter == 1 && token.equals("-exit"))
                {
                    jsonConverter.loadToJSON(name, nb);
                    notStopped = false;
                    break;
                }
                else if (counter == 1 && token.equals("-add"))
                {
                    nb.addRecord(new NoteBookRecord(s.next(), LocalDateTime.now(), s.next()));
                }
                else if (counter == 1 && token.equals("-rm"))
                {
                    nb.removeRecord(s.next());
                }
                else if (counter == 1 && token.equals("-show"))
                {
                    if (s.hasNext())
                    //notebook -show 14.12.2019 7:00 17.12.2019 13:00 МоЯ Твоя мне
                    {
                        String heh[] = new String[4] ;
                        ArrayList<String> titles = new ArrayList<String>();
                        int i =0 ;
                        while(i < 4 && s.hasNext())
                        {
                            heh[i] = s.next();
                            i++;
                        }
                        if(i < 4)
                        {
                            System.out.println("wrong input");
                            continue;
                        }
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyyHH:mm");
                        heh[1] = checkStrings(heh[1]);
                        heh[3] = checkStrings(heh[3]);
                        String f = heh[0].concat(heh[1]);
                        String t = heh[2].concat(heh[3]);
                        LocalDateTime from = LocalDateTime.parse(f, formatter);
                        LocalDateTime to = LocalDateTime.parse(t, formatter);
                        while(s.hasNext()) {
                            titles.add(s.next());
                        }
                        nb.showFilteredRecords(from, to, titles);
                    }
                    else
                    {
                        nb.showRecords();
                    }
                }
                counter++;
            }
        }

    }
}

