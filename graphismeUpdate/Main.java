import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Point[][] init_interface(){
        Fenetre.createFenetre(700, 700, "MasterMind"); // Creation de la fenetre avec cette methode de classe ; la fenetre se gere seule par la suite.

        Point a = new Point(100, 100);
        Point b = new Point(200, 100);
        Point c = new Point(300, 100);
        Point d = new Point(400, 100);

        Point e = new Point(100, 200);
        Point f = new Point(200, 200);
        Point g = new Point(300, 200);
        Point h = new Point(400, 200);


        Point k = new Point(100, 300);
        Point l = new Point(200, 300);
        Point m = new Point(300, 300);
        Point n = new Point(400, 300);


        Point o = new Point(100, 400);
        Point p = new Point(200, 400);
        Point q = new Point(300, 400);
        Point r = new Point(400, 400);

        Point s = new Point(100, 500);
        Point t = new Point(200, 500);
        Point u = new Point(300, 500);
        Point v = new Point(400, 500);



        Point[][] tous_les_point = {{a, b, c, d}, {e, f, g, h}, {k, l, m, n}, {o, p, q, r}, {s, t, u, v}};


        return tous_les_point;

    }


    public static Point[][] refaire_interface(Point[][] tous_les_point, Test manche, Test coup, int Nummanche) {

        for (int i = 0; i < tous_les_point.length; i++) {

            for (int j = 0; j < tous_les_point[i].length; j++) {
                tous_les_point[i][j].setColor(128, 128, 128);
            }

        }


        manche.setText("Vous en êtes à la manche " + Nummanche + " et ");
        coup.setText(" au premier coup");
        return tous_les_point;

    }

    public static void affiche_interface(Point[][] tous_les_point, int[][] cod, int nbCoup, Test manche, Test coup, int Nummanche) {


        for (int i = 0; i < cod.length; i++) {

            if (i < nbCoup) {

                for (int j = 0; j < cod[i].length; j++) {

                    if (cod[i][j] == 1) {
                        tous_les_point[i][j].setColor(255, 0, 0);
                    } else if (cod[i][j] == 2) {
                        tous_les_point[i][j].setColor(0, 255, 0);
                    } else if (cod[i][j] == 3) {
                        tous_les_point[i][j].setColor(0, 0, 255);
                    } else if (cod[i][j] == 4) {
                        tous_les_point[i][j].setColor(255, 255, 255);
                    } else if (cod[i][j] == 0) {
                        tous_les_point[i][j].setColor(0, 0, 0);
                    }

                }
            }


            manche.setText("Vous en êtes à la manche " + Nummanche + " et ");
            coup.setText(" au coup " + nbCoup + ".");



        }


    }


    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................

    // fonctions classiques sur les tableaux

    /**
     * pré-requis : nb >= 0
     * résultat : un tableau de nb entiers égaux à val
     */
    public static int[] initTab(int nb, int val) {

        int[] tab = new int[nb];
        for (int i = 0; i < nb; i++) {
            tab[i] = val;
        }

        return tab;

    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : une copie de tab
     */
    public static int[] copieTab(int[] tab) {

        int[] tab2 = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {
            tab2[i] = tab[i];
        }

        return tab2;

    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : la liste des éléments de t entre parenthèses et séparés par des virgules
     */
    public static String listElem(char[] t) {

        String str = "";

        str += "(" + t[0];

        for (int i = 1; i < t.length; i++) {
            str += "," + t[i];
        }
        str += ")";
        return str;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : le plus grand indice d'une case de t contenant c s'il existe, -1 sinon
     */
    public static int plusGrandIndice(char[] t, char c) {

        boolean contient = false;
        int ind = 0;

        for (int i = 0; i < t.length; i++) {
            if (t[i] == c) {
                contient = true;
                ind = i;
            }
        }

        if (!contient) {
            return -1;
        } else {
            return ind;
        }


    }
    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi c'est un élément de t
     * stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean estPresent(char[] t, char c) {

        int result = plusGrandIndice(t, c);

        return result != -1;

    }

    //______________________________________________

    /**
     * pré-requis : aucun;
     * action : affiche un doublon et 2 de ses indices dans t s'il en existe
     * résultat : vrai ssi les éléments de t sont différents.
     * Stratégie : utilise la fonction plusGrandIndice.
     */
    public static boolean elemDiff(char[] t) {
        for (int i = 0; i < t.length; i++) {
            if (i != plusGrandIndice(t, t[i])) {
                System.out.println(t[i] + " est un doublon et ses indices dans le tableau sont " + i + " et " + plusGrandIndice(t, t[i]));
                System.out.println("\n------------------------------\n");
                return false;
            }

        }
        return true;
    }

    //______________________________________________

    /**
     * pré-requis : t1.length = t2.length
     * résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
     */
    public static boolean sontEgaux(int[] t1, int[] t2) {

        for (int i = 0; i < t1.length; i++) {
            if (t1[i] != t2[i]) {
                return false;
            }
        }
        return true;

    }

    //______________________________________________

    // Dans toutes les fonctions suivantes, on a comme prérequis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents.

    // fonctions sur les codes pour la manche Humain

    /**
     * pré-requis : aucun
     * résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
     */
    public static int[] codeAleat(int lgCode, int nbCouleurs) {

        int[] tab = new int[lgCode];

        for (int i = 0; i < lgCode; i++) {
            Random rn = new Random();
            int r = rn.nextInt(nbCouleurs);
            tab[i] = r;
        }

        return tab;

    }

    //____________________________________________________________

    /**
     * pré-requis : aucun.
     * Action : si codMot n'est pas correct, affiche pourquoi
     * résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
     */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs) {

        if (codMot.length() != lgCode) {
            System.out.println("Votre mot n'est pas de la bonne longueur.");
            return false;
        } else {
            for (int i = 0; i < codMot.length(); i++) {
                //on va chercher la fonction estPresent pour savoir si le mot est correct
                boolean est_present = estPresent(tabCouleurs, codMot.charAt(i));
                if (!est_present) {
                    System.out.println("La lettre  " + i + " de votre mot n'est pas bonne.");
                    return false;
                }
            }
        }
        return true;

    }

    //____________________________________________________________

    /**
     * prérequis : les caractères de codMot sont des éléments de tabCouleurs
     * résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
     */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {

        int[] tab = new int[codMot.length()];

        // on cherche l'indice grâce à la 3° fonction plusGrandIndice et on la met dans le tableau final
        for (int i = 0; i < codMot.length(); i++) {
            tab[i] = plusGrandIndice(tabCouleurs, codMot.charAt(i));
        }

        return tab;

    }

    //____________________________________________________________

    /**
     * pré-requis : aucun.
     * action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce
     * qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
     * résultat : le code saisi sous forme de tableau d'entiers
     */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {

        // on demande la saisie
        System.out.println("\n------------------------------\n");
        System.out.println("Vous êtes a l'essai n° " + nbCoups);
        System.out.println("\n------------------------------\n");
        System.out.println("Veuillez saisir votre Code couleur : ");
        Scanner myObj4 = new Scanner(System.in);
        String demande = myObj4.nextLine();
        System.out.println("\n------------------------------\n");

        // tant que la saisie n'est pas bonne on redemande
        while (!codeCorrect(demande, lgCode, tabCouleurs)) {
            System.out.println("\n------------------------------\n");
            System.out.println("Votre saisie n'est pas correcte");
            System.out.println("Veuillez saisir votre Code couleur : ");
            myObj4 = new Scanner(System.in);
            demande = myObj4.nextLine();
            System.out.println("\n------------------------------\n");


        }
        //on retourne le code sous forme de tableau d'entier (grâce à la fonction motVersEntiers
        return motVersEntiers(demande, tabCouleurs);

    }

    //____________________________________________________________

    /**
     * pré-requis : cod1.length = cod2.length
     * résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
     */
    public static int nbBienPlaces(int[] cod1, int[] cod2) {

        int compteur = 0;

        for (int i = 0; i < cod1.length; i++) {
            if (cod1[i] == cod2[i]) {
                compteur++;
            }

        }

        return compteur;

    }

    //____________________________________________________________

    /**
     * prérequis : les éléments de cod sont des entiers de 0 à nbCouleurs-1
     * résultat : un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de "i" dans cod
     * Par exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
     */
    public static int[] tabFrequence(int[] cod, int nbCouleurs) {

        int[] freq = new int[nbCouleurs];

        // pour chaque valeur de cod, on prend cette valeur qui correspond donc à l'indice du tableau freq et on ajoute 1
        for (int i = 0; i < cod.length; i++) {
            freq[cod[i]]++;
        }
        return freq;
    }

    //____________________________________________________________

    /**
     * pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     * résultat : le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1")
     */
    public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] freqCod1 = tabFrequence(cod1, nbCouleurs);
        int[] freqCod2 = tabFrequence(cod2, nbCouleurs);
        int nbCommuns = 0;
        for (int i = 0; i < nbCouleurs; i++) {
            if (freqCod1[i] < freqCod2[i]) {
                nbCommuns += freqCod1[i];
            } else {
                nbCommuns += freqCod2[i];
            }
        }
        return nbCommuns - nbBienPlaces(cod1, cod2);
    }

    //____________________________________________________________

    /**
     * pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     * résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
     * se trouvant  (resp. ne se trouvant pas) au même indice
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne (1,2) : 1 bien placé (le "0" à l'indice 3)
     * et 2 mal placés (1 "0" et 1 "1")
     */
    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
        int nbBienPlaces = nbBienPlaces(cod1, cod2);
        int nbCommuns = nbCommuns(cod1, cod2, nbCouleurs);
        return new int[]{nbBienPlaces, nbCommuns};
    }


    // Affichage du tableau


    //____________________________________________________________

    //.........................................................................
    // MANCHEHUMAIN
    //.........................................................................

    /**
     * pré-requis : numMache >= 1
     * action : effectue la (numManche)ème manche où l'ordinateur est le codeur et l'humain le décodeur
     * (le paramètre numManche ne sert que pour l'affichage)
     * résultat :
     * - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du joueur humain (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     * - sinon le nombre de codes proposés par le joueur humain
     */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax, Point[][] grille, Test manche, Test coup) {

        System.out.println("\n------------------------------\n");
        System.out.println("Vous êtes à la manche " + numManche + ".");
        int[] cod1 = codeAleat(lgCode, tabCouleurs.length);
        int[][] sauvegardeCode = new int[nbEssaisMax][lgCode];
        int[][] sauvegardeRep = new int[nbEssaisMax][2];
        for (int i = 1; i <= nbEssaisMax; i++) {
            System.out.println("\n------------------------------\n");
            System.out.println("Vous pouvez choisir entre ses couleurs : " + listElem(tabCouleurs));
            sauvegardeCode[i - 1] = propositionCodeHumain(i, lgCode, tabCouleurs);
            if (nbBienMalPlaces(cod1, sauvegardeCode[i - 1], tabCouleurs.length)[0] == lgCode) {
                System.out.println("\n------------------------------\n");
                System.out.println("!!! vous avez trouvé le code !!!");
                System.out.println("\n------------------------------\n");

                return i;
            } else {
                sauvegardeRep[i - 1] = nbBienMalPlaces(cod1, sauvegardeCode[i - 1], tabCouleurs.length);

                affichePlateau(sauvegardeCode, sauvegardeRep, i, tabCouleurs);
                affiche_interface(grille, sauvegardeCode, i,  manche, coup, numManche);

            }
        }

        char[] couleurs_a_trouver = new char[cod1.length];
        for (int i = 0; i < cod1.length; i++) {
            couleurs_a_trouver[i] = tabCouleurs[cod1[i]];
        }
        System.out.print("Vous avez atteint le nombre d'essaies maximum. \nLe code à trouver était ");
        System.out.println(listElem(couleurs_a_trouver));
        System.out.println("\n------------------------------\n");

        return sauvegardeRep[nbEssaisMax - 1][1] + 2 * (lgCode - (sauvegardeRep[nbEssaisMax - 1][0] + sauvegardeRep[nbEssaisMax - 1][1]));
    }

    //____________________________________________________________

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    /**
     * pré-requis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
     * résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
     */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs) {
        String codMot = "";
        for (int i = 0; i < cod.length; i++) {
            codMot += tabCouleurs[cod[i]];
        }
        return codMot;
    }

    //___________________________________________________________________

    /**
     * pré-requis : rep.length = 2
     * action : si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont
     * les nombres de bien et mal placés resp.
     * résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
     */
    public static boolean repCorrecte(int[] rep, int lgCode) {
        if (rep.length != 2) return false;
        else if (rep[0] < 0) {
            System.out.println("Vous ne pouvez pas donner un nombre négatif de couleurs bien placées : ");
            System.out.println("\n------------------------------\n");
            return false;
        } else if (rep[1] < 0) {
            System.out.println("Vous ne pouvez pas donner un nombre négatif de couleurs mal placées : ");
            System.out.println("\n------------------------------\n");
            return false;
        } else if (rep[0] + rep[1] > lgCode) {
            System.out.println("Vos deux nombres ne doivent pas dépasser la longueur du code : ");
            System.out.println("\n------------------------------\n");
            return false;
        } else return true;
    }

    //___________________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir les nombres de bien et mal placés,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : les réponses du joueur humain dans un tableau à 2 entiers
     */
    public static int[] reponseHumain(int lgCode) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Donnez le nombre de couleur(s) bien placée(s) : ");
        int nbBienplaces = scn.nextInt();
        System.out.println("\n------------------------------\n");
        System.out.println("Donnez le nombre de couleur(s) mal placée(s) : ");
        int nbMalplaces = scn.nextInt();
        System.out.println("\n------------------------------\n");
        int[] rep = {nbBienplaces, nbMalplaces};
        while (!repCorrecte(rep, lgCode)) {
            System.out.println("Donnez le nombre de couleur(s) bien placée(s) : ");
            nbBienplaces = scn.nextInt();
            System.out.println("\n------------------------------\n");
            System.out.println("Donnez le nombres de couleur(s) mal placée(s) : ");
            nbMalplaces = scn.nextInt();
            System.out.println("\n------------------------------\n");
            rep[0] = nbBienplaces;
            rep[1] = nbMalplaces;
        }
        return rep;
    }

    //___________________________________________________________________

    /**
     * CHANGE : action si le code suivant n'existe pas
     * ************************************************
     * pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
     * action/résultat : met dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble
     * des codes à valeurs  de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
     * sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
    public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {

        for (int i = cod1.length - 1; i >= 0; i--) {
            if (cod1[i] < nbCouleurs - 1) {
                cod1[i]++;
                return true;
            } else if (cod1[i] == nbCouleurs - 1) {
                cod1[i] = 0;
            } else {
                for (int j = 0; j < cod1.length; j++) {
                    cod1[j] = 0;
                }
                return false;
            }
        }
        for (int j = 0; j < cod1.length; j++) {
            cod1[j] = 0;
        }
        return false;
    }

    //___________________________________________________________________

    /**
     * CHANGE : ajout du paramètre cod1 et modification des spécifications
     * ********************************************************************
     * pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
     * nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
     * résultat : vrai ssi cod1 est compatible avec les nbCoups premières lignes de cod et de rep,
     * c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups premières
     * propositions de cod seraient les nbCoups premières réponses de rep resp.
     */

    public static boolean estCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {

        for (int i = 0; i < nbCoups; i++) {

            int[] nbBienMalPlaces = nbBienMalPlaces(cod[i], cod1, nbCouleurs);
            if (!sontEgaux(nbBienMalPlaces, rep[i])) {
                return false;
            }
        }

        return true;
    }

    //___________________________________________________________________

    /**
     * CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat,
     * ajout du paramètre cod1 et modification des spécifications
     * *************************************************************************
     * pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
     * nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
     * action/résultat : met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
     * des codes à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
     * cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
     * sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
    public static boolean passeCodeSuivantLexicoCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {

        while (passeCodeSuivantLexico(cod1, nbCouleurs)) {
            if (estCompat(cod1, cod, rep, nbCoups, nbCouleurs)) {
                return true;
            }
        }
        return false;

    }

    //___________________________________________________________________

    // manche Ordinateur

    /**
     * pré-requis : numManche >= 2
     * action : effectue la (numManche)ème manche où l'humain est le codeur et l'ordinateur le décodeur
     * (le paramètre numManche ne sert que pour l'affichage)
     * résultat :
     * - 0 si le programme détecte une erreur dans les réponses du joueur humain
     * - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de l'ordinateur (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     * - sinon le nombre de codes proposés par l'ordinateur
     */
    public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax, Point[][] grille, Test manche, Test coup) {
        System.out.println("\n------------------------------\n");
        System.out.println("Vous êtes à la manche " + numManche + ".");
        int[][] sauvegardeCode = new int[nbEssaisMax][lgCode];
        int[][] sauvegardeRep = new int[nbEssaisMax][2];


        sauvegardeCode[0] = initTab(lgCode, 0);
        System.out.println("Voici le code proposé par l'ordinateur.");
        System.out.println("\n------------------------------\n");
        affichePlateau(sauvegardeCode, sauvegardeRep, 1, tabCouleurs);
        affiche_interface(grille, sauvegardeCode, 1,  manche, coup, numManche);
        sauvegardeRep[0] = reponseHumain(lgCode);
        if (sauvegardeRep[0][0] == lgCode) {
            System.out.println("!!! L'IA a trouvé le bon code !!!");
            return 1;
        }

        for (int i = 1; i < nbEssaisMax; i++) {
            int[] cod1 = copieTab(sauvegardeCode[i - 1]);
            if (!passeCodeSuivantLexicoCompat(cod1, sauvegardeCode, sauvegardeRep, i, tabCouleurs.length)) {

                System.out.println("Vous vous êtes trompé dans vos saisies.");
                System.out.println("\n------------------------------\n");
                System.out.println("Veuillez rentrer votre code secret.");
                Scanner myObj4 = new Scanner(System.in);
                String mot_a_trouver = myObj4.nextLine();
                System.out.println("\n------------------------------\n");
                afficheErreurs(mot_a_trouver, sauvegardeCode, sauvegardeRep, i, lgCode, tabCouleurs);

                return 0;
            }
            sauvegardeCode[i] = cod1;
            System.out.println("Voici le code proposé par l'ordinateur.");
            System.out.println("\n------------------------------\n");
            affichePlateau(sauvegardeCode, sauvegardeRep, i + 1, tabCouleurs);
            affiche_interface(grille, sauvegardeCode, i+1,  manche, coup, numManche);
            sauvegardeRep[i] = reponseHumain(lgCode);

            if (sauvegardeRep[i][0] == lgCode) {
                System.out.println("!!! L'IA a trouvé le bon code !!!");

                return i + 1;
            }

        }

        System.out.println("Vous vous êtes trompé dans vos saisies.");
        System.out.println("\n------------------------------\n");
        System.out.println("Veuillez rentrer votre code secret.");
        Scanner myObj4 = new Scanner(System.in);
        String mot_a_trouver = myObj4.nextLine();
        System.out.println("\n------------------------------\n");
        afficheErreurs(mot_a_trouver, sauvegardeCode, sauvegardeRep, nbEssaisMax, lgCode, tabCouleurs);

        return sauvegardeRep[nbEssaisMax - 1][1] + 2 * (lgCode - (sauvegardeRep[nbEssaisMax - 1][1] + sauvegardeRep[nbEssaisMax - 1][0]));

    }

    //___________________________________________________________________

    //.........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    //.........................................................................


    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir un entier strictement positif,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : l'entier strictement positif saisi
     */
    public static int saisirEntierPositif() {

        System.out.println("Veuillez saisir un entier positif :");
        Scanner scan = new Scanner(System.in);
        int demande = scan.nextInt();
        System.out.println("\n------------------------------\n");

        // tant que la saisie n'est pas bonne on redemande
        while (demande < 1) {
            System.out.println("Votre saisie n'est pas correcte. Veuillez saisir un entier positif :");
            scan = new Scanner(System.in);
            demande = scan.nextInt();
            System.out.println("\n------------------------------\n");

        }
        return demande;
    }

    //___________________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir un entier pair strictement positif,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : l'entier pair strictement positif saisi
     */
    public static int saisirEntierPairPositif() {

        System.out.println("Veuillez saisir un entier positif pair :");
        Scanner scan = new Scanner(System.in);
        int demande = scan.nextInt();
        System.out.println("\n------------------------------\n");

        // tant que la saisie n'est pas bonne on redemande
        while (demande < 1 || demande % 2 == 1) {
            System.out.println("Votre saisie n'est pas correcte. Veuillez saisir un entier positif pair :");
            scan = new Scanner(System.in);
            demande = scan.nextInt();
            System.out.println("\n------------------------------\n");

        }
        return demande;
    }


    //___________________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir le nombre de couleurs (stricement positif),
     * puis les noms de couleurs aux initiales différentes,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : le tableau des initiales des noms de couleurs saisis
     */
    public static char[] saisirCouleurs() {

        System.out.println("Combien de couleurs Voulez-vous ?");
        int nbCOuleurs = saisirEntierPositif();
        System.out.println("Veuillez rentrer les initiales des couleurs.");
        char[] couleurs = new char[nbCOuleurs];
        Scanner myObj4 = new Scanner(System.in);
        String demande = myObj4.nextLine();

        for (int i = 0; i < demande.length(); i++) {
            couleurs[i] = demande.charAt(i);
        }

        while (!elemDiff(couleurs) || !demande.equals(demande.toUpperCase())) {
            if (!elemDiff(couleurs)) {
                System.out.println("Il y a eu des doublons, veuillez les supprimer.");
            } else {
                System.out.println("Les lettres ne sont pas en majuscule.");
            }
            System.out.println("Veuillez rentrer les initiales des couleurs.");
            myObj4 = new Scanner(System.in);
            demande = myObj4.nextLine();
            System.out.println("\n------------------------------\n");

            for (int i = 0; i < demande.length(); i++) {
                couleurs[i] = demande.charAt(i);
            }
        }

        return couleurs;

    }

    //___________________________________________________________________
    // ETENDU
    //___________________________________________________________________


    // on demande à l'utilisateur sa réponse

    /**
     * pré-requis : cod est une matrice, rep est une matrice à 2 colonnes,
     * 60 <= nbCoups < cod.length, nbCoups < rep.length,
     * les éléments de cod sont des entiers de 0 à tabCouleurs.length -1
     * et codMot est incorrect ou incompatible avec les nbCoups
     * premières lignes de cod et de rep
     * action : affiche les erreurs d’incorrection ou d’incompatibilité
     */
    public static void afficheErreurs(String codMot, int[][] cod, int[][] rep, int nbCoups, int lgCode, char[] tabCouleurs) {

        System.out.println("\n------------------------------\n");
        System.out.println("Vérification de votre code couleur...");
        if (!codeCorrect(codMot, lgCode, tabCouleurs)) {
            System.out.println("Votre choix de code de base n'était donc pas bon.");
        }

        int[] codmot_entier = motVersEntiers(codMot, tabCouleurs);
        System.out.println("\n------------------------------\n");
        System.out.println("Vérification de vos réponses...\n");
        System.out.println("\n------------------------------\n");

        for (int i = 0; i < nbCoups; i++) {

            int[] nbBienMalPlaces = nbBienMalPlaces(cod[i], codmot_entier, tabCouleurs.length);
            if (!sontEgaux(nbBienMalPlaces, rep[i])) {
                System.out.println("Votre réponse " + i + " (" + rep[i][0] + "," + rep[i][1] + ") n'est pas vraie.");
            }
        }

    }

    // Affichage du tableau


    public static void affichePlateau(int[][] cod, int[][] rep, int nbCoups, char[] tabCouleurs) {

        // on affiche les côtés ordi caché lors de la partie
        System.out.print("\n\n     ||");
        for (int i = 0; i < cod[0].length; i++) {
            if (i == cod[0].length - 1) System.out.print("///");
            else System.out.print("////");
        }
        System.out.print("||\n");


        System.out.print("     ||");
        for (int i = 0; i < cod[0].length; i++) {
            if (i == cod[0].length - 1) System.out.print("   ");
            else System.out.print("    ");
        }
        System.out.print("||\n");

        System.out.print(" BP  ||");
        for (int i = 0; i < cod[0].length; i++) {
            System.out.print(" ? |");

        }
        System.out.print("|  MP");

        System.out.print("\n     ||");
        for (int k = 0; k < cod[0].length; k++) {
            if (k == cod[0].length - 1) System.out.print("---");
            else System.out.print("----");
        }
        System.out.print("||\n");

        System.out.print("     ||");
        for (int i = 0; i < cod[0].length; i++) {
            if (i == cod[0].length - 1) System.out.print("   ");
            else System.out.print("    ");
        }
        System.out.print("||\n");


        for (int i = 0; i < cod.length; i++) {
            System.out.print("[" + rep[i][0] + "]  ");
            System.out.print("||");
            for (int j = 0; j < cod[0].length; j++) {
                if (i < nbCoups) System.out.print(" " + tabCouleurs[cod[i][j]] + " |");
                else System.out.print("   |");
            }
            System.out.print("|");
            System.out.print("  [" + rep[i][1] + "]");

            System.out.print("\n     ||");
            for (int k = 0; k < cod[0].length; k++) {
                if (k == cod[0].length - 1) System.out.print("---");
                else System.out.print("----");
            }
            System.out.print("||\n");
        }


        System.out.print("     ||");
        for (int i = 0; i < cod[0].length; i++) {
            if (i == cod[0].length - 1) System.out.print("///");
            else System.out.print("////");
        }
        System.out.print("||\n");
    }


    public static void statsMasterMindIA(int lgcode, char[] tabCouleurs) {

        int[] tous_les_code = initTab(lgcode, 0);
        int[][] codes_maximums = new int[1][lgcode];
        codes_maximums[0] = tous_les_code;
        int moyenne = 0;
        int compteur = 0;
        int maximum = 0;


        for (int j = 0; j < (Math.pow(tabCouleurs.length, lgcode)); j++) {

            int code_trouve = test_code(tous_les_code, lgcode, tabCouleurs);
            moyenne += code_trouve;

            if (code_trouve > maximum) {
                codes_maximums = new int[1][lgcode];
                codes_maximums[0] = copieTab(tous_les_code);
                maximum = code_trouve;
            } else if (code_trouve == maximum) {
                int[][] copie_codes_maximums = new int[codes_maximums.length][lgcode];
                for (int i = 0; i < codes_maximums.length; i++) {
                    copie_codes_maximums[i] = copieTab(codes_maximums[i]);
                }
                codes_maximums = new int[codes_maximums.length + 1][lgcode];

                for (int i = 0; i < copie_codes_maximums.length; i++) {
                    codes_maximums[i] = copieTab(copie_codes_maximums[i]);
                }

                codes_maximums[copie_codes_maximums.length] = copieTab(tous_les_code);

            }
            passeCodeSuivantLexico(tous_les_code, tabCouleurs.length);
            compteur++;
        }

        System.out.println("Le code sera trouvé en moyenne au bout de " + moyenne / compteur + " essaies.");
        System.out.println("Les codes qui seront le plus efficaces (trouvés en " + maximum + " coups) sont : ");
        for (int i = 0; i < codes_maximums.length; i++) {
            System.out.println(entiersVersMot(codes_maximums[i], tabCouleurs));
        }
        System.out.println("\n------------------------------\n");

    }


    public static int test_code(int[] cod_a_trouver, int lgCode, char[] tabCouleurs) {

        int[][] sauvegardeCode = new int[(int) Math.pow(tabCouleurs.length, lgCode)][lgCode];
        int[][] sauvegardeRep = new int[(int) Math.pow(tabCouleurs.length, lgCode)][2];
        sauvegardeCode[0] = initTab(lgCode, 0);
        sauvegardeRep[0] = nbBienMalPlaces(sauvegardeCode[0], cod_a_trouver, tabCouleurs.length);

        if (sauvegardeRep[0][0] == lgCode) {
            return 1;
        }

        for (int i = 1; i < (Math.pow(tabCouleurs.length, lgCode)); i++) {

            int[] cod1 = copieTab(sauvegardeCode[i - 1]);

            passeCodeSuivantLexicoCompat(cod1, sauvegardeCode, sauvegardeRep, i, tabCouleurs.length);


            sauvegardeCode[i] = cod1;

            sauvegardeRep[i] = nbBienMalPlaces(sauvegardeCode[i], cod_a_trouver, tabCouleurs.length);
            if (sauvegardeRep[i][0] == lgCode) {
                return i + 1;
            }

        }


        return 0;


    }

    ;


    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................


    /**
     * CHANGE : ajout de : le nombre d'essais maximum doit être strictement positif
     * *****************************************************************************
     * action : demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs,
     * nbManches, nbEssaisMax),
     * effectue la partie et affiche le résultat (identité du gagnant ou match nul).
     * La longueur d'un code, le nombre de couleurs et le nombre d'essais maximum doivent être strictement positifs.
     * Le nombre de manches doit être un nombre pair strictement positif.
     * Les initiales des noms de couleurs doivent être différentes.
     * Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
     */
    public static void main(String[] args) {


        System.out.println("\n\n\n /$$      /$$                       /$$                               /$$      /$$ /$$                 /$$");
        System.out.println("| $$$    /$$$                      | $$                              | $$$    /$$$|__/                | $$");
        System.out.println(("| $$$$  /$$$$  /$$$$$$   /$$$$$$$ /$$$$$$    /$$$$$$   /$$$$$$       | $$$$  /$$$$ /$$ /$$$$$$$   /$$$$$$$"));
        System.out.println("| $$ $$/$$ $$ |____  $$ /$$_____/|_  $$_/   /$$__  $$ /$$__  $$      | $$ $$/$$ $$| $$| $$__  $$ /$$__  $$");
        System.out.println("| $$  $$$| $$  /$$$$$$$|  $$$$$$   | $$    | $$$$$$$$| $$  \\__/      | $$  $$$| $$| $$| $$  \\ $$| $$  | $$");
        System.out.println("| $$\\  $ | $$ /$$__  $$ \\____  $$  | $$ /$$| $$_____/| $$            | $$\\  $ | $$| $$| $$  | $$| $$  | $$");
        System.out.println("| $$ \\/  | $$|  $$$$$$$ /$$$$$$$/  |  $$$$/|  $$$$$$$| $$            | $$ \\/  | $$| $$| $$  | $$|  $$$$$$$");
        System.out.println("|__/     |__/ \\_______/|_______/    \\___/   \\_______/|__/            |__/     |__/|__/|__/  |__/ \\_______/");
        System.out.println("\n\n\n------------------------------\n\nBienvenue sur notre MasterMind\n\n------------------------------\n\n\n  ");


        System.out.println("La longueur du code à trouver est 4.");
        int lgCode = 4;
        System.out.println("\n------------------------------\n");


        System.out.println("Combien de manches voulez-vous faire ?");
        int numManche = saisirEntierPairPositif();
        System.out.println("\n------------------------------\n");


        System.out.println("Le nombre d'essaie maximum est 5");
        int nbEssaisMax = 5;
        System.out.println("\n------------------------------\n");

        // on demande tabCouleurs
        System.out.println("Vous avez les choix entre 6 couleurs : \nNoir (N)\nRouge (R) \nVert (V) \n Bleu (B) \n Blanc (L)");
        char[] tabCouleurs = {'N','R', 'V', 'B', 'L'};

        statsMasterMindIA(lgCode, tabCouleurs);

        Point[][] grille = init_interface();
        Test manche = new Test(Fenetre.manche);
        Test coup = new Test(Fenetre.coup);


        int score_joueur = 0;
        int score_ordi = 0;

        for (int i = 1; i <= numManche; i++) {

            System.out.println("\n------------------------------\n");

            if (i % 2 == 1) {

                System.out.println("C'est à votre tour d'être le codeur.");
                System.out.println("\n------------------------------\n");

                grille = refaire_interface(grille, manche, coup, i);
                int M_h = mancheHumain(lgCode, tabCouleurs, i, nbEssaisMax, grille, manche, coup);

                score_ordi += M_h;
                System.out.println("Le score de l'IA est : " + score_ordi);
                System.out.println("\n------------------------------\n");


            } else {

                System.out.println("C'est au tour de notre IA d'être le codeur.");
                System.out.println("\n------------------------------\n");
                grille = refaire_interface(grille, manche, coup, numManche);
                int M_o = mancheOrdinateur(lgCode, tabCouleurs, i, nbEssaisMax, grille, manche, coup);

                if (M_o != 0) {
                    score_joueur += M_o;
                    System.out.println("Votre score est : " + score_joueur);
                    System.out.println("\n------------------------------\n");
                } else {
                    System.out.println("Vous vous êtes trompé dans vos saisies. Passons à la manche suivante.");
                }

            }


        }

        System.out.println("----------------- \n\n");
        System.out.println("C'est la fin de la partie. \nVotre score final est : " + score_joueur + ". \nLe score final de l'IA est : " + score_ordi + ".");
        System.out.println("\n------------------------------\n");

        if (score_joueur < score_ordi) {
            System.out.println("Vous avez gagné la parti !!! \nMerci d'avoir joué avec nous.");
        } else if (score_joueur == score_ordi) {
            System.out.println("vous avez fait égalité !!! \nMerci d'avoir joué avec nous.");
        } else {
            System.out.println("Vous avez perdu la parti ... \nMerci d'avoir joué avec nous.");
        }

    } // fin main


}




