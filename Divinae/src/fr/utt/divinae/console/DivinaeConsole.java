package fr.utt.divinae.console;

public class DivinaeConsole {

	public static void main(String[] args) {
		System.out.println("Bienvenue dans Divinae !");
		InterfaceConsole interfacePartie = new InterfaceConsole();
		interfacePartie.lancerUnePartie();
	}

}
