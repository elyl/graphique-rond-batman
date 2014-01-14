class ProjetFinal extends Program {
    // Sortie du programme
    void exitProgram (String s) {
	if (length(s) == 1 && (s.charAt(0) == 'Q' || s.charAt(0) == 'q'))
	    System.exit(0);
    }

    // Selection et controle du mode
    boolean isCorrectMode (String s) {
	if (length(s) != 1) {
	    return false;
	} else {
	    if (s.charAt(0) == '1' | s.charAt(0) == '2' | s.charAt(0) == '3' | s.charAt(0) == '4')
		return true;
	    else
		return false;
	}
    }

    void uncorrectMode (String s) {
	while (isCorrectMode(s) == false) {
	    print("Saisie incorrecte, recommence : ");
	    s = readString();
	    exitProgram(s);
	}
    }

    void switchMode (String s) {
	exitProgram(s);
	if (isCorrectMode(s) == false)
	    uncorrectMode(s);
    }

    // Selection et controle du niveau de difficulte
    boolean isCorrectLevel (String s) {
	if (length(s) != 1) {
	    return false;
	} else {
	    if (s.charAt(0) == '1' | s.charAt(0) == '2' | s.charAt(0) == '3')
		return true;
	    else
		return false;
	}
    }

    void uncorrectLevel (String s) {
	while (isCorrectLevel(s) == false) {
	    print("Saisie incorrecte, recommence : ");
	    s = readString();
	    exitProgram(s);
	}	
    }

    void switchLevel (String s) {
	exitProgram(s);
	if (isCorrectLevel(s) == false)
	    uncorrectLevel(s);
    }

    // Execution du mode choisi au niveau desire
    void modeExecution (String s) {
	if (s.charAt(0) == '1')
	    modeAdd();
    }

    boolean isAnInt (String s) {
	for (int i = 0; i < length(s); i++) {
	    if (s.charAt(i) > '9' || s.charAt(i) < '0')
		return false;
	}
	return true;
    }

    void notAnInt (String s) {
	while (isAnInt(s) == false) {
	    print("Saisie incorrecte, recommence : ");
	    s = readString();
	    exitProgram(s);
	}
    }

    void modeAdd () {
	int nb1, nb2, resultat;
	String reponse;
	
	nb1 = (int)(Math.random()*10);
	nb2 = (int)(Math.random()*10);

	resultat = nb1 + nb2;
	print(nb1 + " + " + nb2 + " = ");
	reponse = readString();
	switchAdd(reponse);
	if (Integer.parseInt(reponse) == resultat)
	    println("Bonne reponse !");
	else
	    println("Mauvaise reponse, la bonne reponse etait " + resultat + ".");
    }

    void switchAdd (String s) {
	exitProgram(s);
	if (isAnInt(s) == false)
	    notAnInt(s);
    }

    void algorithm () {
	boolean infinity = true;
	String mode;
	String level;

	println("Salut. N'oublie pas que tu peux quitter ce programme a tout moment en appuyant sur la touche Q, puis sur la touche Entree." + '\n');

	while (infinity == true) {
	    println("1 : Addition" + '\n' + "2 : Soustraction" + '\n' + "3 : Multiplication" + '\n' + "4 : Probleme" + '\n');
	    print("Saisis ton mode de travail : ");
	    mode = readString();
	    switchMode(mode);
	    println();
	    
	    println("1 : Facile" + '\n' + "2 : Moyen" + '\n' + "3 : Difficile" + '\n');
	    print("Saisis ton niveau : ");
	    level = readString();
	    switchLevel(level);
	    println();

	    modeExecution(mode);
	}
    }
}