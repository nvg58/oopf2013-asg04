// Board.java
package oop.asg04;

import java.util.Arrays;

/**
 * CS108 Tetris Board.
 * Represents a Tetris board -- essentially a 2-d grid
 * of booleans. Supports tetris pieces and row clearing.
 * Has an "undo" feature that allows clients to add and remove pieces efficiently.
 * Does not do any drawing or have any idea of pixels. Instead,
 * just represents the abstract 2-d board.
 */
public class Board {
  // Some ivars are stubbed out for you:
  private int width;
  private int height;
  private boolean[][] grid;
  private boolean DEBUG = true;
  boolean committed;

  private int[] heights;
  private int[] widths;
  private int maxHeight;

  // for backup
  private boolean[][] backupGrid;
  private int[] backupHeights;
  private int[] backupWidths;
  private int backupMaxHeight;

  // Here a few trivial methods are provided:

  /**
   * Creates an empty board of the given width and height
   * measured in blocks.
   */
  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    grid = new boolean[width][height];
    committed = true;

    // CODE HERE
    for (int i = 0; i < width; ++i) {
      for (int j = 0; j < height; ++j) {
        grid[i][j] = false;
      }
    }

    heights = new int[width];
    widths = new int[height];
    maxHeight = 0;

    backupGrid = new boolean[width][height];
    backupHeights = new int[width];
    backupWidths = new int[height];
    backupMaxHeight = maxHeight;
  }


  /**
   * Returns the width of the board in blocks.
   */
  public int getWidth() {
    return width;
  }


  /**
   * Returns the height of the board in blocks.
   */
  public int getHeight() {
    return height;
  }


  /**
   * Returns the max column height present in the board.
   * For an empty board this is 0.
   */
  public int getMaxHeight() {
    // CODE HERE
    return maxHeight;
  }


  /**
   * Checks the board for internal consistency -- used
   * for debugging.
   */
  public void sanityCheck() {
    if (DEBUG) {
      // CODE HERE
      for (int y = 0; y < getHeight(); ++y) {
        int temp = 0;
        for (int x = 0; x < getWidth(); ++x) {
//          System.out.println("x " + x + "y " + y);
          if (grid[x][y]) {
            temp++;
//            System.out.println("temp: " + temp);
          }
        }
//        System.out.println("---------" + widths.length);
//        System.out.println(Arrays.toString(widths));
//        System.out.println(height);
        if (temp != widths[y]) {
//          System.out.println(this.toString());
          throw new RuntimeException("wrong width at row: " + y + "\nexpected: " + temp + "\nactual: " + widths[y]);
        }
      }

      for (int x = 0; x < getWidth(); ++x) {
        int y = getHeight() - 1;
        while (y >= 0 && !grid[x][y]) {
          --y;
        }
        if (y + 1 != heights[x]) {
//          System.out.println(this.toString());
          throw new RuntimeException("wrong height at column: " + x + "\nexpected: " + y + 1 + "\nactual: " + heights[x]);
        }
      }

      int tempMaxHeight = 0;
      for (int i = 0; i < heights.length; ++i) {
        if (tempMaxHeight < heights[i]) {
          tempMaxHeight = heights[i];
        }
      }
      if (tempMaxHeight != getMaxHeight()) {
        throw new RuntimeException("wrong maxHeight\n expected " + tempMaxHeight + "\nactual: " + getMaxHeight());
      }
    }
  }

  /**
   * Given a piece and an x, returns the y
   * value where the piece would come to rest
   * if it were dropped straight down at that x.
   * <p/>
   * <p/>
   * Implementation: use the skirt and the col heights
   * to compute this fast -- O(skirt length).
   */
  public int dropHeight(Piece piece, int x) {
    // CODE HERE
    int res = 0;
    int pieceSkirt[] = piece.getSkirt();
//    System.out.println(Arrays.toString(pieceSkirt));
//    System.out.println(Arrays.toString(piece.getBody()));
//    System.out.println(piece.getWidth());
//    System.out.println(toString());
    for (int i = 0; i < piece.getWidth(); ++i) {
      int t = Math.max(0, heights[x + i] - pieceSkirt[i]);
      if (t > res)
        res = t;
    }
//    System.out.println(res);
    return res;
  }


  /**
   * Returns the height of the given column --
   * i.e. the y value of the highest block + 1.
   * The height is 0 if the column contains no blocks.
   */
  public int getColumnHeight(int x) {
    // CODE HERE
    return heights[x];
  }


  /**
   * Returns the number of filled blocks in
   * the given row.
   */
  public int getRowWidth(int y) {
    // CODE HERE
    return widths[y];
  }


  /**
   * Returns true if the given block is filled in the board.
   * Blocks outside of the valid width/height area
   * always return true.
   */
  public boolean getGrid(int x, int y) {
    // CODE HERE
    if (x > width || y > height)
      return true;
    return grid[x][y];
  }


  public static final int PLACE_OK = 0;
  public static final int PLACE_ROW_FILLED = 1;
  public static final int PLACE_OUT_BOUNDS = 2;
  public static final int PLACE_BAD = 3;

  /**
   * Attempts to add the body of a piece to the board.
   * Copies the piece blocks into the board grid.
   * Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
   * for a regular placement that causes at least one row to be filled.
   * <p/>
   * <p>Error cases:
   * A placement may fail in two ways. First, if part of the piece may falls out
   * of bounds of the board, PLACE_OUT_BOUNDS is returned.
   * Or the placement may collide with existing blocks in the grid
   * in which case PLACE_BAD is returned.
   * In both error cases, the board may be left in an invalid
   * state. The client can use undo(), to recover the valid, pre-place state.
   */
  public int place(Piece piece, int x, int y) {
    // flag !committed problem
    if (!committed) throw new RuntimeException("place commit problem");

    int result = PLACE_OK;

    // CODE HERE
    committed = false;
//    System.out.println(piece.toString());
    // backup board to undo()
    for (int i = 0; i < getWidth(); i++) {
      System.arraycopy(grid[i], 0, backupGrid[i], 0, grid[i].length);
    }
    System.arraycopy(heights, 0, backupHeights, 0, heights.length);
    System.arraycopy(widths, 0, backupWidths, 0, widths.length);
    backupMaxHeight = getMaxHeight();
//    System.out.println(backupMaxHeight + "----");

    if (piece.getWidth() + x > width || piece.getHeight() + y > height || x < 0 || y < 0)
      return PLACE_OUT_BOUNDS;
    TPoint[] pieceBody = piece.getBody();
//    System.out.println(piece.toString());
//    System.out.println(pieceBody.length);
    for (int i = 0; i < pieceBody.length; ++i) {
      int px = pieceBody[i].x + x;
      int py = pieceBody[i].y + y;
//      System.out.print(i + " - ");
//      System.out.println(px + " " + py);
//      System.out.println(pieceBody[i].toString());
      if (getGrid(px, py))
        return PLACE_BAD;
      grid[px][py] = true;
      if (py + 1 > heights[px])
        heights[px] = py + 1;
      if (maxHeight < heights[px])
        maxHeight = heights[px];
      widths[py]++;
//      System.out.println("width " + py + " = " + widths[py]);
//      System.out.println("height " + px + " = " + heights[px]);
      if (widths[py] == width)
        result = PLACE_ROW_FILLED;
    }
    if (result != PLACE_OK)
      sanityCheck();

//    System.out.println(backupMaxHeight + "//////");
    return result;
  }


  /**
   * Deletes rows that are filled all the way across, moving
   * things above down. Returns the number of rows cleared.
   */
  public int clearRows() {
    int rowsCleared = 0;
    // CODE HERE

    // backup board to undo()
    for (int i = 0; i < getWidth(); i++) {
      System.arraycopy(grid[i], 0, backupGrid[i], 0, grid[i].length);
    }
    System.arraycopy(heights, 0, backupHeights, 0, heights.length);
    System.arraycopy(widths, 0, backupWidths, 0, widths.length);
    backupMaxHeight = getMaxHeight();

//    System.out.println(getMaxHeight());
    for (int y = 0; y < getMaxHeight(); ++y) {
//      System.out.println("row " + y + " = " + getRowWidth(y));
      if (getRowWidth(y) == width) {
//        System.out.println("aaa" + getRowWidth(y));
        rowsCleared++;
        for (int yy = y + 1; yy <= getMaxHeight(); ++yy) {
//          System.out.println("aaaaa" + widths[yy]);
          widths[yy - 1] = widths[yy];
          for (int x = 0; x < width; ++x) {
            grid[x][yy - 1] = grid[x][yy];
          }
        }
        for (int x = 0; x < getWidth(); ++x) {
          grid[x][maxHeight - 1] = false;
        }
        widths[maxHeight - 1] = 0;
//        System.out.println(y+ "=>>>>>>    \n" + this.toString());
        --y;
      }
    }
//    System.out.println(this.toString());

    for (int x = 0; x < getWidth(); ++x) {
      int y = getHeight() - 1;
      while (y >= 0 && !grid[x][y]) {
        --y;
      }
      heights[x] = y + 1;
    }
//    System.out.println(Arrays.toString(heights));
    maxHeight = 0;
    for (int i = 0; i < heights.length; ++i) {
      if (maxHeight < heights[i]) {
        maxHeight = heights[i];
      }
    }
//    System.out.println(Arrays.toString(heights));

    sanityCheck();
    committed = false;
    return rowsCleared;
  }


  /**
   * Reverts the board to its state before up to one place
   * and one clearRows();
   * If the conditions for undo() are not met, such as
   * calling undo() twice in a row, then the second undo() does nothing.
   * See the overview docs.
   */
  public void undo() {
    // YOUR CODE HERE
    if (!committed) {
      committed = true;
      boolean[][] tmpGrid = grid;
      grid = backupGrid;
      backupGrid = tmpGrid;

      int[] h = heights;
      heights = backupHeights;
      backupHeights = h;

      int[] w = widths;
      widths = backupWidths;
      backupWidths = w;

      int mH = maxHeight;
      maxHeight = backupMaxHeight;
      backupMaxHeight = mH;
    }
    sanityCheck();
  }


  /**
   * Puts the board in the committed state.
   */
  public void commit() {
    committed = true;
  }


  /*
   Renders the board state as a big String, suitable for printing.
   This is the sort of print-obj-state utility that can help see complex
   state change over time.
   (provided debugging utility)
   */
  public String toString() {
    StringBuilder buff = new StringBuilder();
    for (int y = height - 1; y >= 0; y--) {
      buff.append('|');
      for (int x = 0; x < width; x++) {
        if (getGrid(x, y)) buff.append('*');
        else buff.append(' ');
      }
      buff.append("|\n");
    }
    for (int x = 0; x < width + 2; x++) buff.append('-');
    return (buff.toString());
  }
}


