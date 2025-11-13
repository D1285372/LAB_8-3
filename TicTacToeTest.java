// TicTacToeTest.java

import org.junit.Assert;
import org.junit.Test;

public class TicTacToeTest {

    @Test
    public void testInitialState() {
        TicTacToe game = new TicTacToe();
        Assert.assertFalse("遊戲開始時不應結束", game.isGameOver());
        Assert.assertEquals("遊戲開始時應為 O 玩家", 'O', game.getCurrentPlayer());
        Assert.assertEquals("遊戲開始時沒有勝利者", 'N', game.getWinner());
        Assert.assertEquals("遊戲開始時所有格子應為空白", ' ', game.getCell(1, 1));
    }

    @Test
    public void testValidMoveAndPlayerSwitch() {
        TicTacToe game = new TicTacToe();


        Assert.assertTrue("O 下棋應成功", game.set(0, 0));
        Assert.assertEquals("O 應佔據 (0, 0)", 'O', game.getCell(0, 0));
        Assert.assertEquals("玩家應切換為 X", 'X', game.getCurrentPlayer());


        Assert.assertTrue("X 下棋應成功", game.set(1, 1));
        Assert.assertEquals("X 應佔據 (1, 1)", 'X', game.getCell(1, 1));
        Assert.assertEquals("玩家應切換為 O", 'O', game.getCurrentPlayer());
    }

    @Test
    public void testInvalidMoves() {
        TicTacToe game = new TicTacToe();


        Assert.assertFalse("下在 (-1, 0) 應失敗", game.set(-1, 0));
        Assert.assertFalse("下在 (3, 0) 應失敗", game.set(3, 0));


        game.set(0, 0);
        Assert.assertFalse("下在已佔用的 (0, 0) 應失敗", game.set(0, 0));


        game.set(1, 0); // X
        game.set(0, 1); // O
        game.set(2, 0); // X
        game.set(0, 2); // O 勝利

        Assert.assertTrue(game.isGameOver());
        Assert.assertEquals('O', game.getWinner());

        Assert.assertFalse("遊戲結束後下棋應失敗", game.set(2, 2));
    }

    @Test
    public void testOWinByRow() {
        TicTacToe game = new TicTacToe();

        game.set(0, 0); // O
        game.set(1, 0); // X
        game.set(0, 1); // O
        game.set(2, 0); // X
        game.set(0, 2); // O 獲勝 (第 0 行)

        Assert.assertTrue("O 應獲勝", game.isGameOver());
        Assert.assertEquals("O 是勝利者", 'O', game.getWinner());


        Assert.assertEquals("勝利者 (O) 應為當前玩家", 'O', game.getCurrentPlayer());
    }

    @Test
    public void testXWinByDiagonal() {
        TicTacToe game = new TicTacToe();

        game.set(0, 1); // O
        game.set(0, 0); // X
        game.set(1, 0); // O
        game.set(1, 1); // X
        game.set(2, 1); // O
        game.set(2, 2); // X

        Assert.assertTrue("X 應獲勝", game.isGameOver());
        Assert.assertEquals("X 是勝利者", 'X', game.getWinner());
    }

    @Test
    public void testTieGame() {
        TicTacToe game = new TicTacToe();

        // 井字遊戲平手盤面
        game.set(0, 0); // O
        game.set(0, 1); // X
        game.set(0, 2); // O
        game.set(1, 1); // X
        game.set(2, 0); // O
        game.set(1, 0); // X
        game.set(1, 2); // O
        game.set(2, 2); // X
        game.set(2, 1); // O

        Assert.assertTrue("遊戲應平手", game.isGameOver());
        Assert.assertEquals("結果應為平手 'T'", 'T', game.getWinner());
    }
}