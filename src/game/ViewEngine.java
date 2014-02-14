package game;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import utils.ListOfPlayers;
import utils.Value;
import view.GameControlPanel;
import view.OutputPanel;
import view.GamePanel;
import view.StatisticsPanel;

public class ViewEngine extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int frameWidth = 700, frameHeight = 500, tabWidth = frameWidth - 100, tabHeight = frameHeight - 100;
	
	private String title = "Game";
	
	private JTabbedPane tbPane = new JTabbedPane();

	private GameEngine gameEngine;
	
	private GamePanel gamePanel;
	private OutputPanel outputPanel;
	private StatisticsPanel statsPanel;
	private GameControlPanel gameControlPanel;

	public ViewEngine(GameEngine gE, ListOfPlayers players){
		this.gameEngine = gE;
		initFrame();
		initComponents(players);
		addComponents();
	}
	
	private void initComponents(ListOfPlayers pls){
		gameControlPanel = new GameControlPanel(this);
		gamePanel = new GamePanel(gameEngine, pls);
		outputPanel = new OutputPanel(pls);
		statsPanel = new StatisticsPanel(pls);
		tbPane.setPreferredSize(new Dimension(tabWidth, tabHeight));
		addWindowListener(new java.awt.event.WindowAdapter() {
	        public void windowClosing(WindowEvent winEvt) {
	        	outputPanel.end();
	        	gameEngine.endGame();
	        }
	    });
	}
	
	private void addComponents(){
		tbPane.addTab("Game", gamePanel);
		tbPane.addTab("Console", outputPanel);
		tbPane.addTab("Statistics", statsPanel);
		add(tbPane, BorderLayout.CENTER);
		add(gameControlPanel, BorderLayout.NORTH);
		pack();
	}
	
	private void initFrame(){
		setTitle(title);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		setLocation(50, screenSize.height/2 - frameHeight/2);
		setLocation(screenSize.width/2 - frameWidth/2, screenSize.height/2 - frameHeight/2);
		setLayout(new BorderLayout());
		setVisible(true);
		setResizable(false);
	}
	
	public void setSpeed(int speed){
		gameEngine.setSpeed(speed);
	}
	
	public void setGameViewVisible(boolean yes){
		gamePanel.canRun(yes);
	}
	
	public void setOutputViewVisible(boolean yes){
		outputPanel.canRun(yes);
	}
	
	public void setRounds(int i){
		gameControlPanel.setRound(i);
	}
	
	public void stopGame(){
		gameEngine.stopGame();
	}	
	
	public void continueGame(){
		gameEngine.continueGame();
	}
	
	public void addStats(Card c1, Card c2){
		statsPanel.statsCards(c1, c2);
	}
	
	public void newRound(){
			gamePanel.newRound();
	}
	
	public void setFlop(Card c1, Card c2, Card c3){
			gamePanel.setFlop(c1, c2, c3);
	}
	
	public void setTurn(Card c){
			gamePanel.setTurn(c);
	}
	
	public void setRiver(Card c){
			gamePanel.setRiver(c);
	}
	
	public void setState(int ID, Value.state state){
			gamePanel.setState(ID, state);
	}

	//****** player's actions *****//
	
	public void fold(int ID){
		if (outputPanel != null)
			outputPanel.fold(ID);
			gamePanel.fold(ID);
	}
	
	public void check(int ID){
		if (outputPanel != null)
			outputPanel.check(ID);
			gamePanel.check(ID);
	}
	
	public void raise(int ID, double chips){
		if (outputPanel != null)
			outputPanel.raise(ID, chips);
			gamePanel.raise(ID, chips);
	}
	
	public void call(int ID, double chips){
		if (outputPanel != null)
			outputPanel.call(ID, chips);
			gamePanel.call(ID, chips);
	}

	public void dealCard(int ID, Card c1, Card c2){
		if (outputPanel != null)
			outputPanel.dealedCard(ID, c1, c2);
			gamePanel.dealedCards(ID, c1, c2);
	}
	
}
