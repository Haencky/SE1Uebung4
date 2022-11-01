import persistence.PersistanceStrategyStream;
import persistence.PersistenceException;
import persistence.PersistenceStrategy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Container c = Container.getInstance();
    static PersistenceStrategy PS = new PersistanceStrategyStream();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.printf("Hallo mit 'help' erhalten sie eine Erklärung über die Funktionen dieses Programms\n");
        String eingabe = "";
        while (!eingabe.equals("exit")) {
            eingabe = sc.next();
            if(eingabe.startsWith("enter")) {
                List<Expertise> kompetenzen = new ArrayList<>();
                Integer id = Integer.parseInt(sc.next());
                String vorName = sc.next();
                String name = sc.next();
                String rolle = sc.next();
                String abteilung = sc.next();
                String buf = "";
                while (true) {
                    buf = sc.next();
                    if(buf.equals(".")){
                        break;
                    }else {
                        kompetenzen.add(new Expertise(buf, Integer.parseInt(sc.next())));
                    }
                }
                addNewMitarbeiter(id,vorName,name,rolle,abteilung,kompetenzen);
            } else if (eingabe.equals("store")) {
                c.setPers(PS);
                try {
                    c.store(c.getCurrentList());
                } catch (PersistenceException e) {
                    e.getMessage();
                }
            } else if (eingabe.equals("load merge")) {
                //TODO: 11/1/22 das halt noch machen
            } else if (eingabe.equals("load force")) {
                // TODO: 11/1/22  laden und dabei die aktuelle Liste überschreiben
                try {
                    c.load();
                } catch (PersistenceException e) {
                    e.printStackTrace();
                }
            } else if (eingabe.equals("help")) {
                printHelp();

            } else if (eingabe.equals("dump")) {
                List<Member> liste = c.getCurrentList();
                //System.out.println("---------------------------------------------------------------------------------------");
                System.out.printf("%3s %15s %15s %15s %15s %15s", "ID", "Vorname", "Name", "Rolle", "Abteilung","Kompetenzen");
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------------");

                for(Member m : liste) {
                    System.out.format("%3d %15s %15s %15s %15s %15d", m.getID(),m.getVorName(),m.getName(),m.getRolle(),m.getAbteilung(),m.getAnzahlExpertisen());
                    System.out.println();
                }
                System.out.println("---------------------------------------------------------------------------------------");
            }
        }
        sc.close();
    }
    public static  void addNewMitarbeiter(Integer id, String vorName, String name, String rolle, String abteilung, List<Expertise> expertisen) {
        try {
            Member m = new Mitarbeiter(id,vorName,name,rolle,abteilung);
            m.addExpertise(expertisen);
            c.addMember(m);
        } catch (ContainerException e) {
            e.getMessage();
        }
    }

    public static void printHelp() {
        System.out.printf("Das ist das Hilfe-Menue dieser Anwendung\n");
        System.out.printf("Sie können folgende Befehle ausführen: \n");
        System.out.printf("\tenter: fügt ein neues Mitglied in ihren Container hinzu\n");
        System.out.printf("\tDer Aufruf fuer enter muss wie folgt aussehen:\n");
        System.out.printf("\t\t enter [ID] [Vorname] [Name] [Rolle] [Abteilung] [Kompetenzname 1] [Level 1] [...] [Kompetenzname n] [Level n]  .\n");
        System.out.printf("\tstore: speichert ihre aktuelle Liste persistent auf ihrem Gerät ab\n");
        System.out.printf("\tload: kann entweder mit 'merge' oder mit 'force aufgerufen werden'\n");
        System.out.printf("\tdump: Listet den Inhalt des Containers auf\n");
        System.out.printf("\texit: Beendet die Anwendung\n");
        System.out.printf("\thelp: bietet hilfe fuer Befehle an\n");
    }
}