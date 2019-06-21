/**
* Permet de resoudre une grille de sudoku .
*/
public final class ResoudreGrille {
  /**
   * Pour cacher le contructeur.
   */
  private ResoudreGrille() { }
  /**
   * Point d'entrée du programe.
   * @param args liste d'argument
   */
  public static void main(final String[] args) {
    GrilleImpl lagrille = new GrilleImpl();
    lagrille.displayGrille();
  }
}
