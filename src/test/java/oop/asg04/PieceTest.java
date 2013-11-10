package oop.asg04;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest {
  private Piece pyr1, pyr2, pyr3, pyr4, pyr5;
  private Piece stick1 , stick2 ;
  private Piece l11 , l12 , l13 , l14 ;
  private Piece l21 , l22 , l23 , l24 ;
  private Piece s11 , s12 ;
  private Piece s21 , s22 ;
  private Piece square ;
  private Piece[] arrPieces;

  @Before
  public void setUp() throws Exception {

    pyr1 = new Piece(Piece.PYRAMID_STR);
    pyr2 = pyr1.computeNextRotation();
    pyr3 = pyr2.computeNextRotation();
    pyr4 = pyr3.computeNextRotation();
    pyr5 = pyr4.computeNextRotation();

    s11 = new Piece(Piece.S1_STR);
    s12 = s11.computeNextRotation();

    s21 = new Piece(Piece.S2_STR);
    s22 = s21.computeNextRotation();

    stick1 = new Piece(Piece.STICK_STR);
    stick2 = stick1.computeNextRotation();

    l11 = new Piece(Piece.L1_STR);
    l12 = l11.computeNextRotation();
    l13 = l12.computeNextRotation();
    l14 = l13.computeNextRotation();

    l21 = new Piece(Piece.L2_STR);
    l22 = l21.computeNextRotation();
    l23 = l22.computeNextRotation();
    l24 = l23.computeNextRotation();

    square = new Piece(Piece.SQUARE_STR);

    arrPieces = Piece.getPieces();

  }

  // Here are some sample tests to get you started

  @Test
  public void testSampleSize() {
    // Check size of pyr piece
    assertEquals(3, pyr1.getWidth());
    assertEquals(2, pyr1.getHeight());

    // Now try after rotation
    // Effectively we're testing size and rotation code here
    assertEquals(2, pyr2.getWidth());
    assertEquals(3, pyr2.getHeight());

    assertEquals(3, pyr3.getWidth());
    assertEquals(2, pyr3.getHeight());
    assertEquals(2, pyr4.getWidth());
    assertEquals(3, pyr4.getHeight());

    assertEquals(pyr1, pyr5);
    // Now try with some other piece, made a different way
    Piece l = new Piece(Piece.STICK_STR);
    assertEquals(1, l.getWidth());
    assertEquals(4, l.getHeight());
  }


  // Test the skirt returned by a few pieces
  @Test
  public void testSampleSkirt() {
    // Note must use assertTrue(Arrays.equals(... as plain .equals does not work
    // right for arrays.
    assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));

    assertTrue(Arrays.equals(new int[] {1, 0}, pyr2.getSkirt()));

    assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));


    assertTrue(Arrays.equals(new int[] {0, 0, 1}, s11.getSkirt()));
    assertTrue(Arrays.equals(new int[] {1, 0}, s12.getSkirt()));
  }

  @Test
  public void testSkirtofStick() {
    assertTrue(Arrays.equals(new int[]{0}, stick1.getSkirt()));
    assertTrue(Arrays.equals(new int[]{0, 0, 0, 0}, stick2.getSkirt()));
  }

  @Test
  public void testSizeofStick() {
    assertEquals(1, stick1.getWidth());
    assertEquals(4, stick1.getHeight());
    assertEquals(4, stick2.getWidth());
    assertEquals(1, stick2.getHeight());
  }

  @Test
  public void testSkirtofL1() {
    assertTrue(Arrays.equals(new int[]{0, 0}, l11.getSkirt()));
    assertTrue(Arrays.equals(new int[]{0, 0, 0}, l12.getSkirt()));
    assertTrue(Arrays.equals(new int[]{2, 0}, l13.getSkirt()));
    assertTrue(Arrays.equals(new int[]{0, 1, 1}, l14.getSkirt()));
  }

  @Test
  public void testSizeOfL1() {
    assertEquals(2, l11.getWidth());
    assertEquals(3, l11.getHeight());
    assertEquals(3, l12.getWidth());
    assertEquals(2, l12.getHeight());
    assertEquals(2, l13.getWidth());
    assertEquals(3, l13.getHeight());
    assertEquals(3, l14.getWidth());
    assertEquals(2, l14.getHeight());
  }

  @Test
  public void testSkirtOfL2() {
    assertTrue(Arrays.equals(new int[]{0, 0}, l21.getSkirt()));
    assertTrue(Arrays.equals(new int[]{1, 1, 0}, l22.getSkirt()));
    assertTrue(Arrays.equals(new int[]{0, 2}, l23.getSkirt()));
    assertTrue(Arrays.equals(new int[]{0, 0, 0}, l24.getSkirt()));
  }

  @Test
  public void testSizeOfL2() {
    assertEquals(2, l21.getWidth());
    assertEquals(3, l21.getHeight());
    assertEquals(3, l22.getWidth());
    assertEquals(2, l22.getHeight());
    assertEquals(2, l23.getWidth());
    assertEquals(3, l23.getHeight());
    assertEquals(3, l24.getWidth());
    assertEquals(2, l24.getHeight());
  }

  @Test
  public void testSkirtOfS1() {
    assertTrue(Arrays.equals(new int[]{0, 0, 1}, s11.getSkirt()));
    assertTrue(Arrays.equals(new int[]{1, 0}, s12.getSkirt()));
  }

  @Test
  public void testSizeOfS1() {
    assertEquals(3, s11.getWidth());
    assertEquals(2, s11.getHeight());
    assertEquals(2, s12.getWidth());
    assertEquals(3, s12.getHeight());
  }

  @Test
  public void testSkirtOfS2() {
    assertTrue(Arrays.equals(new int[]{1, 0, 0}, s21.getSkirt()));
    assertTrue(Arrays.equals(new int[]{0, 1}, s22.getSkirt()));
  }

  @Test
  public void testSizeOfS2() {
    assertEquals(3, s21.getWidth());
    assertEquals(2, s21.getHeight());
    assertEquals(2, s22.getWidth());
    assertEquals(3, s22.getHeight());
  }

  @Test
  public void testSkirtOfSquare() {
    assertTrue(Arrays.equals(new int[]{0, 0}, square.getSkirt()));
  }

  @Test
  public void testSizeOfSquare() {
    assertEquals(2, square.getWidth());
    assertEquals(2, square.getHeight());
  }

  @Test
  public void testFastRotationofStick() {
    assertTrue(stick1.equals(arrPieces[0]));
    assertTrue(stick2.equals(arrPieces[0].fastRotation()));
    assertTrue(stick1.equals(arrPieces[0].fastRotation().fastRotation()));
  }

  @Test
  public void testFastRotationofS1() {
    assertTrue(s11.equals(arrPieces[3]));
    assertTrue(s12.equals(arrPieces[3].fastRotation()));
    assertTrue(s11.equals(arrPieces[3].fastRotation().fastRotation()));
  }

  @Test
  public void testFastRotationofS2() {
    assertTrue(s21.equals(arrPieces[4]));
    assertTrue(s22.equals(arrPieces[4].fastRotation()));
    assertTrue(s21.equals(arrPieces[4].fastRotation().fastRotation()));
  }

  @Test
  public void testFastRotationofL1() {
    assertTrue(l11.equals(arrPieces[1]));
    assertTrue(l12.equals(arrPieces[1].fastRotation()));
    assertTrue(l13.equals(arrPieces[1].fastRotation().fastRotation()));
    assertTrue(l14.equals(arrPieces[1].fastRotation().fastRotation().fastRotation()));
    assertTrue(l11.equals(arrPieces[1].fastRotation().fastRotation().fastRotation().fastRotation()));
  }

  @Test
  public void testFastRotationofL2() {
    assertTrue(l21.equals(arrPieces[2]));
    assertTrue(l22.equals(arrPieces[2].fastRotation()));
    assertTrue(l23.equals(arrPieces[2].fastRotation().fastRotation()));
    assertTrue(l24.equals(arrPieces[2].fastRotation().fastRotation().fastRotation()));
    assertTrue(l21.equals(arrPieces[2].fastRotation().fastRotation().fastRotation().fastRotation()));
  }

  @Test
  public void testFastRotationofSquare() {
    assertTrue(square.equals(arrPieces[5]));
    assertTrue(square.equals(arrPieces[5].fastRotation()));
  }

  @Test
  public void testFastRotationofPyramid() {
    assertTrue(pyr1.equals(arrPieces[6]));
    assertTrue(pyr2.equals(arrPieces[6].fastRotation()));
    assertTrue(pyr3.equals(arrPieces[6].fastRotation().fastRotation()));
    assertTrue(pyr4.equals(arrPieces[6].fastRotation().fastRotation().fastRotation()));
    assertTrue(pyr1.equals(arrPieces[6].fastRotation().fastRotation().fastRotation().fastRotation()));
  }

  @Test
  public void testEqualPieces() {
    assertFalse(s12.equals(pyr2));

    assertFalse(l11.equals(l21));
    assertFalse(l12.equals(l24));

    assertFalse(l24.equals(pyr1));
    assertFalse(l24.equals(pyr1));

    assertFalse(pyr4.equals(s22));
    assertFalse(pyr1.equals(l12));
  }
}
