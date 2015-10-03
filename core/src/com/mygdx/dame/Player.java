package com.mygdx.dame;

import java.util.ArrayList;

public class Player {

	private Token[] tokens = new Token[12];
	int ID = 0; 
	private boolean playercanMove;
	
	public Player(String pfad, int ID, ArrayList<Asset> board){
		this.ID = ID;
		if(ID == 0){
			playercanMove = true;
			for(int i = 0; i < 12; i++){
				if(((i *2 ) / 8) % 2 != 0){
					tokens[i] = new Token(pfad, board, i *2 + 1);
					tokens[i].setPlayer(this);
				}else{
					tokens[i] = new Token(pfad, board, i *2);
					tokens[i].setPlayer(this);
				}
			}
		}else{
			playercanMove = false;
			for(int i = 0; i < 12; i++){
				if(((i *2 ) / 8) % 2 != 0){
					tokens[i] = new Token(pfad, board, 62 -(i * 2 -1));
					tokens[i].setPlayer(this);
			}else{
				tokens[i] = new Token(pfad, board, 62 -(i * 2));
				tokens[i].setPlayer(this);
			}
			}
		}
	}
	
	public int getRemainingTokenCount(){
		return tokens.length;
	}

	public boolean getPlayerCanMove(){
		return playercanMove;
	}
	
	public void setPlayerCanMove(boolean canMove){
		playercanMove = canMove;
	}
	public Token[] getRemainingToken(){
		return tokens;
	}
	
	public int getID(){
		return ID;
	}
}
