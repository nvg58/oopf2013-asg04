package oop.asg04;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/*
  Unit test for Piece class -- starter shell.
  Mã khởi đầu cho unit test dành cho lớp Piece
 */
public class PieceTest222 {
    // You can create data to be used in the your
    // test cases like this. For each run of a test method,
    // a new PieceTest object is created and setUp() is called
    // automatically by JUnit.
    // For example, the code below sets up some
    // pyramid and s pieces in instance variables
    // that can be used in tests.

    // Bạn có thể tạo dữ liệu để dùng trong các 
    // testcase như thế này. Mỗi lần chạy một phương thức test,
    // JUnit tự động tạo một đối tượng PieceTest
    // và gọi setUp().
    // Ví dụ, đoạn mã dưới đây thiết lập (setup)
    // các mảnh pyramid và S trong các biến thực thể
    // mà có thể sử dụng trong các test
    private Piece pyr1, pyr2, pyr3, pyr4;
    private Piece s, sRotated;
    private Piece stick1 , stick2 ;
    private Piece l11 , l12 , l13 , l14 ;
    private Piece l21 , l22 , l23 , l24 ;
    private Piece s11 , s12 ;
    private Piece s21 , s22 ;
    private Piece square ;
    private Piece [ ] arrayOfGetPieces ;

    @Before
    public void setUp() throws Exception {

        pyr1 = new Piece(Piece.PYRAMID_STR);
        pyr2 = pyr1.computeNextRotation();
        pyr3 = pyr2.computeNextRotation();
        pyr4 = pyr3.computeNextRotation();

        s = new Piece(Piece.S1_STR);
        sRotated = s.computeNextRotation();
    }

    // Here are some sample tests to get you started
    // Dưới đây là một số test mẫu để bạn xây dựng tiếp

    //@Test
    public void testSampleSize() {
        // Check size of pyr piece
        // Kiểm tra kích thước mảnh pyramid
        assertEquals(3, pyr1.getWidth());
        assertEquals(2, pyr1.getHeight());

        // Now try after rotation
        // Effectively we're testing size and rotation code here
        // Bây giờ thử lại sau khi xoay
        // Ở đây ta đang test mã xoay mảnh cũng như kích thước.
        assertEquals(2, pyr2.getWidth());
        assertEquals(3, pyr2.getHeight());

        // Now try with some other piece, made a different way
        // Thử một mảnh khác
        Piece l = new Piece(Piece.STICK_STR);
        assertEquals(1, l.getWidth());
        assertEquals(4, l.getHeight());
    }

    // Test the skirt returned by a few pieces
    // Test skirt của các mảnh
    //@Test
    public void testSampleSkirt() {
        // Note must use assertTrue(Arrays.equals(... 
        // as plain .equals does not work right for arrays.
        // Lưu ý, phải dùng assertTrue(Arrays.equals(... 
        // vì .equals() không so sánh được nội dung mảng
        assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
        assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));

        assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
        assertTrue(Arrays.equals(new int[] {1, 0}, sRotated.getSkirt()));
    }
    //@Test
    public void testSampleSizeAndSkirt ( )  {
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

        s11 = new Piece(Piece.S1_STR);
        s12 = s11.computeNextRotation();

        s21 = new Piece(Piece.S2_STR);
        s22 = s21.computeNextRotation();

        square = new Piece(Piece.SQUARE_STR);

        pyr1 = new Piece(Piece.PYRAMID_STR);
        pyr2 = pyr1.computeNextRotation();
        pyr3 = pyr2.computeNextRotation();
        pyr4 = pyr3.computeNextRotation();

        arrayOfGetPieces = Piece.getPieces();
        //Stick part
        assertEquals(1, stick1.getWidth());
        assertEquals(4, stick1.getHeight());
        assertEquals(4, stick2.getWidth());
        assertEquals(1, stick2.getHeight());
        assertTrue(Arrays.equals(new int[]{0}, stick1.getSkirt()));
        assertTrue(Arrays.equals(new int[]{0, 0, 0, 0}, stick2.getSkirt()));
        //L1 part
        assertEquals(2, l11.getWidth());
        assertEquals(3, l11.getHeight());
        assertEquals(3, l12.getWidth());
        assertEquals(2, l12.getHeight());
        assertEquals(2, l13.getWidth());
        assertEquals(3, l13.getHeight());
        assertEquals(3, l14.getWidth());
        assertEquals(2, l14.getHeight());
        assertTrue(Arrays.equals(new int[]{0, 0}, l11.getSkirt()));
        assertTrue(Arrays.equals(new int[]{0, 0, 0}, l12.getSkirt()));
        assertTrue(Arrays.equals(new int[]{2, 0}, l13.getSkirt()));
        assertTrue(Arrays.equals(new int[]{0, 1, 1}, l14.getSkirt()));
        //L2 part
        assertEquals(2, l21.getWidth());
        assertEquals(3, l21.getHeight());
        assertEquals(3, l22.getWidth());
        assertEquals(2, l22.getHeight());
        assertEquals(2, l23.getWidth());
        assertEquals(3, l23.getHeight());
        assertEquals(3, l24.getWidth());
        assertEquals(2, l24.getHeight());
        assertTrue(Arrays.equals(new int[]{0, 0}, l21.getSkirt()));
        assertTrue(Arrays.equals(new int[]{1, 1, 0}, l22.getSkirt()));
        assertTrue(Arrays.equals(new int[]{0, 2}, l23.getSkirt()));
        assertTrue(Arrays.equals(new int[]{0, 0, 0}, l24.getSkirt()));
        //S1 part
        assertEquals(3, s11.getWidth());
        assertEquals(2, s11.getHeight());
        assertEquals(2, s12.getWidth());
        assertEquals(3, s12.getHeight());
        assertTrue(Arrays.equals(new int[]{0, 0, 1}, s11.getSkirt()));
        assertTrue(Arrays.equals(new int[]{1, 0}, s12.getSkirt()));
        //S2 part
        assertEquals(3, s21.getWidth());
        assertEquals(2, s21.getHeight());
        assertEquals(2, s22.getWidth());
        assertEquals(3, s22.getHeight());
        assertTrue(Arrays.equals(new int[]{1, 0, 0}, s21.getSkirt()));
        assertTrue(Arrays.equals(new int[]{0, 1}, s22.getSkirt()));
        //Square part
        assertEquals(2, square.getWidth());
        assertEquals(2, square.getHeight());
        assertTrue(Arrays.equals(new int[]{0, 0}, square.getSkirt()));
        //Pyramid part
        assertEquals(3, pyr1.getWidth());
        assertEquals(2, pyr1.getHeight());
        assertEquals(2, pyr2.getWidth());
        assertEquals(3, pyr2.getHeight());
        assertEquals(3, pyr3.getWidth());
        assertEquals(2, pyr3.getHeight());
        assertEquals(2, pyr4.getWidth());
        assertEquals(3, pyr4.getHeight());
        assertTrue(Arrays.equals(new int[]{0, 0, 0}, pyr1.getSkirt()));
        assertTrue(Arrays.equals(new int[]{1, 0}, pyr2.getSkirt()));
        assertTrue(Arrays.equals(new int[]{1, 0, 1}, pyr3.getSkirt()));
        assertTrue(Arrays.equals(new int[]{0, 1}, pyr4.getSkirt()));

    }

    @Test
    public void testSampleGetPiecesAndEquals ( )  {
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

        s11 = new Piece(Piece.S1_STR);
        s12 = s11.computeNextRotation();

        s21 = new Piece(Piece.S2_STR);
        s22 = s21.computeNextRotation();

        square = new Piece(Piece.SQUARE_STR);

        pyr1 = new Piece(Piece.PYRAMID_STR);
        pyr2 = pyr1.computeNextRotation();
        pyr3 = pyr2.computeNextRotation();
        pyr4 = pyr3.computeNextRotation();

        arrayOfGetPieces = Piece.getPieces();
        assertTrue(stick1.equals(arrayOfGetPieces[0]));
        assertTrue(stick2.equals(arrayOfGetPieces[0].fastRotation()));
        assertTrue(stick1.equals(arrayOfGetPieces[0].fastRotation().fastRotation()));
        assertTrue(l11.equals(arrayOfGetPieces[1]));
        assertTrue(l12.equals(arrayOfGetPieces[1].fastRotation()));
        assertTrue(l13.equals(arrayOfGetPieces[1].fastRotation().fastRotation()));
        assertTrue(l14.equals(arrayOfGetPieces[1].fastRotation().fastRotation().fastRotation()));
        assertTrue(l11.equals(arrayOfGetPieces[1].fastRotation().fastRotation().fastRotation().fastRotation()));
        assertTrue(l21.equals(arrayOfGetPieces[2]));
        assertTrue(l22.equals(arrayOfGetPieces[2].fastRotation()));
        assertTrue(l23.equals(arrayOfGetPieces[2].fastRotation().fastRotation()));
        assertTrue(l24.equals(arrayOfGetPieces[2].fastRotation().fastRotation().fastRotation()));
        assertTrue(l21.equals(arrayOfGetPieces[2].fastRotation().fastRotation().fastRotation().fastRotation()));
        assertTrue(s11.equals(arrayOfGetPieces[3]));
        assertTrue(s12.equals(arrayOfGetPieces[3].fastRotation()));
        assertTrue(s11.equals(arrayOfGetPieces[3].fastRotation().fastRotation()));
        assertTrue(s21.equals(arrayOfGetPieces[4]));
        assertTrue(s22.equals(arrayOfGetPieces[4].fastRotation()));
        assertTrue(s21.equals(arrayOfGetPieces[4].fastRotation().fastRotation()));
        assertTrue(square.equals(arrayOfGetPieces[5]));
        assertTrue(square.equals(arrayOfGetPieces[5].fastRotation()));
        assertTrue(pyr1.equals(arrayOfGetPieces[6]));
        assertTrue(pyr2.equals(arrayOfGetPieces[6].fastRotation()));
        assertTrue(pyr3.equals(arrayOfGetPieces[6].fastRotation().fastRotation()));
        assertTrue(pyr4.equals(arrayOfGetPieces[6].fastRotation().fastRotation().fastRotation()));
        assertTrue(pyr1.equals(arrayOfGetPieces[6].fastRotation().fastRotation().fastRotation().fastRotation()));
    }

    @Test
    public void testSampleGetPiecesAndEquals2 ( )  {
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

        s11 = new Piece(Piece.S1_STR);
        s12 = s11.computeNextRotation();

        s21 = new Piece(Piece.S2_STR);
        s22 = s21.computeNextRotation();

        square = new Piece(Piece.SQUARE_STR);

        pyr1 = new Piece(Piece.PYRAMID_STR);
        pyr2 = pyr1.computeNextRotation();
        pyr3 = pyr2.computeNextRotation();
        pyr4 = pyr3.computeNextRotation();

        arrayOfGetPieces = Piece.getPieces();
        assertFalse(l11.equals(l21));

        assertFalse(l12.equals(l24));
        assertFalse(l24.equals(pyr1));
        assertFalse(pyr1.equals(l12));
        assertFalse(l24.equals(pyr1));
        assertFalse(s12.equals(pyr2));
        assertFalse(pyr4.equals(s22));
    }

}