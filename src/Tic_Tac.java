import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tic_Tac implements ActionListener {

    JFrame frame;
    JPanel titlePanel;
    JPanel buttonPanel;
    JLabel textField;
    Random random = new Random();
    JButton[] buttons = new JButton[9];
    boolean player1 ;
    Color color;
    boolean gameOver = false;

    public Tic_Tac() {

        frame = new JFrame();
        frame.addKeyListener(new MyKeyListener());
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField = new JLabel();
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.GRAY);
        textField.setText("Tic-Tac-Game");
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("TimesNewRoman",Font.PLAIN,80));
        textField.setOpaque(true);

        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(Color.cyan);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("TimesNewRoman",Font.PLAIN,30));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            color = buttons[i].getBackground();
        }

        titlePanel.add(textField);
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel);
        frame.setLocationRelativeTo(null);

        firstTurn();
    }

    private void firstTurn() {

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
        }

        getFirstPlayer();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if(e.getSource() == buttons[i]) {
                if(player1){
                    if(buttons[i].getText().equals("")) {
                        player1 = false;
                        buttons[i].setFont(new Font("INK", Font.PLAIN, 75));
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("X");
                        textField.setText("O turn");
                    }
                } else {
                    if(buttons[i].getText().equals("")) {
                        player1 = true;
                        buttons[i].setFont(new Font("INK", Font.PLAIN, 75));
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("O");
                        textField.setText("X turn");
                    }
                }
                checkWinner();
            }
        }
    }

    private void checkNoWins() {

        int numOfOpenCells = 0;

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                numOfOpenCells++;
            }
        }

        if(numOfOpenCells == 0 ) {
            gameOver = true;
            textField.setText("No win. Press space");
        }
    }

    private void checkWinner() {

        checkNoWins();


        for (int i = 0; i < 3; i++) {

            if(buttons[i].getText().equals("X")
                    && buttons[i + 3].getText().equals("X")
                    && buttons[i + 6].getText().equals("X")){
                xWins(i,i+3,i+6);
            }

            if(buttons[i].getText().equals("O")
                    && buttons[i + 3].getText().equals("O")
                    && buttons[i + 6].getText().equals("O")){
                oWins(i,i+3,i+6);
            }
        }

        for (int i = 0; i < 7; i += 3) {

            if(buttons[i].getText().equals("X")
                    && buttons[i + 1].getText().equals("X")
                    && buttons[i + 2].getText().equals("X")){
                xWins(i,i+1,i+2);
            }

            if(buttons[i].getText().equals("O")
                    && buttons[i + 1].getText().equals("O")
                    && buttons[i + 2].getText().equals("O")){
                oWins(i,i+1,i+2);
            }
        }

        for (int i = 0; i < 1; i++) {

            if(buttons[i].getText().equals("X")
                    && buttons[i + 4].getText().equals("X")
                    && buttons[i + 8].getText().equals("X")){
                xWins(i,i+4,i+8);
            }

            if(buttons[i].getText().equals("O")
                    && buttons[i + 4].getText().equals("O")
                    && buttons[i + 8].getText().equals("O")){
                oWins(i,i+4,i+8);
            }
        }

        for (int i = 2; i < 3; i ++) {

            if(buttons[i].getText().equals("X")
                    && buttons[i + 2].getText().equals("X")
                    && buttons[i + 4].getText().equals("X")){
                xWins(i,i+2,i+4);
            }

            if(buttons[i].getText().equals("O")
                    && buttons[i + 2].getText().equals("O")
                    && buttons[i + 4].getText().equals("O")){
                oWins(i,i+2,i+4);
            }
        }
    }

    private void xWins(int a, int b, int c) {

        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textField.setText("X Wins. Press space");
        gameOver = true;

    }
    private void oWins(int a, int b, int c) {

        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textField.setText("O Wins. Press space");
        gameOver = true;

    }

    private void newGame() {

        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(color);
        }
        getFirstPlayer();

    }

    private void getFirstPlayer() {

        if(random.nextInt(2) == 0) {
            player1 = true;
            textField.setText("X turn");
        } else {
            player1 = false;
            textField.setText("O turn");
        }
    }
    public class MyKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_SPACE && gameOver)  {
                newGame();
                gameOver = false;
                frame.requestFocus();
            }
        }
    }


    public static void main(String[] args) {
        new Tic_Tac();
    }
}
