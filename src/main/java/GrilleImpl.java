import java.util.Arrays;
/**
 * Implementation d'une grille .
 */
public class GrilleImpl implements Grille {
  /** Déclaration de constante. */
  private static final int NEUF = 9;
 /**
  * Caractere de case vide.
  */
 public static final char EMPTY = '@';
 /**
  * Caractere possible a mettre dans la grille.
  * pour une grille 9x9 : 1..9
  * pour une grille 16x16: 0..9-a..f
  */
  public static final char[] POSSIBLE = new char[] {'1', '2', '3', '4', '5', '6',
   '7', '8', '9'};
  /** Tableau de caracteres à deux dimension .*/
  public static final char[][] GRILLE_A_RESOUDRE = new char[][] {
    {'8', '@', '2', '@', '5', '@', '7', '@', '1'},
    {'@', '@', '7', '@', '8', '2', '4', '6', '@'},
    {'@', '1', '@', '9', '@', '@', '0', '@', '@'},
    {'6', '@', '@', '@', '@', '1', '8', '3', '2'},
    {'5', '@', '4', '@', '@', '@', '@', '@', '9'},
    {'1', '8', '@', '3', '@', '@', '@', '@', '6'},
    {'0', '@', '5', '@', '@', '4', '@', '2', '@'},
    {'0', '9', '@', '6', '1', '@', '3', '@', '@'},
    {'3', '@', '8', '@', '9', '@', '6', '@', '7'}
    };
	
/*8 0 2 0 5 0 7 0 1 
0 0 7 0 8 2 4 6 0 
0 1 0 9 0 0 0 0 0 
6 0 0 0 0 1 8 3 2 
5 0 0 0 0 0 0 0 9 
1 8 4 3 0 0 0 0 6 
0 0 0 0 0 4 0 2 0 
0 9 5 6 1 0 3 0 0 
3 0 8 0 9 0 6 0 7 */
  /** Déclaration d'un exemple de grille. */
  private char[][] grille;
  /**
   * Constructeur Grille .
   */
  public GrilleImpl() {
    this.grille = new char[NEUF][NEUF];
    for (int i = 0; i < NEUF; i++) {
      for ( int j = 0; j < NEUF; j++) {
        this.grille[i][j] = GRILLE_A_RESOUDRE[i][j];
      }
    }
  }
  /**
   * Constructeur Grille avec argument .
   */
  public GrilleImpl(char[][] tab) {
    this.grille = new char[NEUF][NEUF];
    for (int i = 0; i < NEUF; i++) {
      for ( int j = 0; j < NEUF; j++) {
        this.grille[i][j] = tab[i][j];
      }
    }
  }
  /**
   * Accesseur de grille.
   * @return retourne la grile
   */
  public final char[][] getGrille() {
    return grille;
  }
  /**
   * Mutateur de grille.
   * @param nouvGille
   */
  public final void setGrille(final char[][] nouvGille) {
    grille = nouvGille;
  }
  /**
   * @return largeur/hauteur de la grille 9 ou 16 .
   */
  public final int getDimension() {
    return grille.length;
  }
  /**
   * Test la presence de la valeur dans la colonne.
   * @param y       position y dans la grille
   * @param value       valeur à rechercher dans la grille
   * @return vrai si la valeur est presente sinon faux
   */
  public boolean checkColumn(final int y, final char value) {
    for (int x = 0; x < NEUF; x++) {
      if (this.grille[x][y] == value) {
        return true;
      }
    }
    return false;
  }
  /**
   * Test la presence de la valeur sur la ligne.
   * @param x       position x dans la grille
   * @param value       valeur à rechercher dans la grille
   * @return vrai si la valeur est presente sinon faux
   */
  public boolean checkLine(final int x, final char value) {
    for (int y = 0; y < NEUF; y++) {
      if (this.grille[x][y] == value) {
        return true;
      }
    }
    return false;
  }
  /**
   * Test la presence de la valeur dans le carré.
   * @param x       position x dans la grille
   * @param y       position y dans la grille
   * @param value       valeur à rechercher dans la grille
   * @return vrai si la valeur est presente sinon faux
   */
  public boolean
  checkSquare(final int x, final int y, final char value) {
    int dimenssion = grille.length;
    int leftpoint = 0;
    int toppoint = 0;
    final int q = 3;
    final int dim = 9;
    if (dimenssion == dim) {
      leftpoint = q * (y / q);
      toppoint = q * (x / q);
    }
    for (int c = leftpoint; c < leftpoint + q; c++) {
      for (int l = toppoint; l < toppoint + q; l++) {
        if (grille[l][c] == value) {
          return true;
        }
      }
    }
    return false;
  }
 /**
  * Affecte une valeur dans la grille.
  * @param x       position x dans la grille
  * @param y       position y dans la grille
  * @param value
  *            valeur a mettre dans la case
  * @throw IllegalArgumentException si x ou y sont hors bornes (0-8)
  * @throw IllegalArgumentException si la valeur est interdite aux vues des
  *        autres valeurs de la grille
  * @throw IllegalArgumentException si value n'est pas un caractere autorise
  *        ('1',...,'9')
  */
  public final void setValue(final int x, final int y, final char value) {
    //int dimenssion = grille.length;
    boolean cont = false;
    cont = Arrays.toString(POSSIBLE).contains(Character.toString(value));
    if (cont == true) {
    //verification
    if ((x >= NEUF) || (x < 0)) {
        throw new IllegalArgumentException("x est hors bornes (0-8)");
    } else if ((y >= NEUF) || (y < 0)) {
          throw new IllegalArgumentException("y est hors bornes (0-8)");
        } else if ((!checkColumn(y, value)) && (!checkLine(x, value))
            && (!checkSquare(x, y, value)) && !(grille[x][y] == EMPTY)) {
            throw new IllegalArgumentException("la valeur est interdite "
                     + "aux vues des autres valeurs de la grille");
            } else {
              grille[x][y] = value;
            }
    } else {
        throw new IllegalArgumentException("La value n'est"
                               + " pas un caractere autorise");
      }
  }
 /**
  * Recupere une valeur de la grille.
  * @param x      position x dans la grille
  * @param y      position y dans la grille
  * @return valeur dans la case x,y
  * @throw IllegalArgumentException si x ou y sont hors bornes (0-8)
  */
  public final char getValue(final int x, final int y) {
    if ((x >= NEUF) || (x < 0)) {
       throw new IllegalArgumentException("x est hors bornes (0-8)");
    } else if ((y >= NEUF) || (y < 0)) {
          throw new IllegalArgumentException("y est hors bornes (0-8)");
        }
    return grille[x][y];
  }
  /**
   * Test si une grille est terminee.
   * @return true si la grille est complete
   */
   public final boolean complete() {
     int dimension = this.getDimension();
     boolean controle = true;
     for (int i = 0; i < dimension; i++) {
        for (int j = 0; j < dimension; j++) {
          if (grille[i][j] == EMPTY) {
            controle = false;
            break;
          }
        }
        if (controle == false) {
          break;
        }
     }
     return controle;
   }
 /**
  * Test si une valeur est possible dans la grille par rapport a ce qu'elle .
  * contient deja
  * @param x     position x dans la grille
  * @param y     position y dans la grille
  * @param value a mettre dans la case
  * @throw IllegalArgumentException si x ou y sont hors bornes (0-8)
  * @throw IllegalArgumentException si value n'est pas un caractere autorise
  *        ('1',...,'9',..)
  * @return true si c'est une valeur possible
  */
  public final boolean possible(final int x, final int y, final char value) {
    boolean cont = false;
    cont = Arrays.toString(POSSIBLE).contains(Character.toString(value));
    if (cont == true) {
      //verification
      if ((x >= NEUF) || (x < 0)) {
        throw new IllegalArgumentException("x est hors bornes (0-8)");
      }
      if ((y >= NEUF) || (y < 0)) {
          throw new IllegalArgumentException("y est hors bornes (0-8)");
      }
      if ((!checkColumn(y, value)) && (!checkLine(x, value))
                  && (!checkSquare(x, y, value)) && (grille[x][y] == EMPTY)) {
            return true;
      } else {
             return false;
         }
    } else {
        throw new IllegalArgumentException("La value n'est pas "
        + "un caractere autorise");
      }
  }
  /**
  * La méthode affiche la grille .
  */
  public void displayGrille() {
    for (int l = 0; l < NEUF; l++) {
      for (int c = 0; c < NEUF; c++) {
        System.out.print(grille[l][c] + " ");
      }
      System.out.println("                  ");
    }
  }
  /** */
  public boolean resoudre() {
    for (int l = 0; l < NEUF; l++) {
      for (int c = 0; c < NEUF; c++) {
        // On parcours les valeurs possibles
        for (int v = 0; v < NEUF; v++) {
          // Si la valeur est possible on l'essai
          if (possible(l, c, POSSIBLE[v])) {
            this.setValue(l, c, POSSIBLE[v]);
			v = NEUF;
		  }
		/*  if (resoudre()){
			  return true;
		  } else {
			  this.setValue(l, c, EMPTY);
		  } */
		}
      }
    } /*
	if (complete()) {
      return true;
	} else {
       resoudre();
	}
	return complete();*/
	return true;
  }
}
