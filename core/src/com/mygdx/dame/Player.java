package com.mygdx.dame;

import java.util.ArrayList;

public class Player {

	private ArrayList<Token> tokens = new ArrayList<Token>();
	int ID = 0; 
	private boolean playercanMove;
	private String pfad;
	
	public Player(String pfad, int ID, ArrayList<Asset> board){	
		this.pfad = pfad;
		this.ID = ID;
		if(ID == 0){
			playercanMove = true;
			for(int i = 0; i < 12; i++){
				if(((i *2 ) / 8) % 2 != 0){
					tokens.add(new Token(pfad, board.get( i *2 + 1), this));
				}else{
					tokens.add(new Token(pfad, board.get(i *2), this));
				}
			}
		}else{
			playercanMove = false;
			for(int i = 0; i < 12; i++){
				if(((i *2 ) / 8) % 2 != 0){
					tokens.add(new Token(pfad, board.get(62 -(i * 2 -1)), this));
			}else{
				tokens.add(new Token(pfad, board.get(62 -(i * 2)), this));
			}
			}
		}
	}

	
	public void addToken(Token t){
		tokens.add(t);
	}
	
	public void removeToken(Token t){
		for(int i = 0; i < tokens.size(); i++){
			if(t.equals(tokens.get(i))){
				tokens.remove(i);
			}
		}
		System.out.println(tokens.size());
		if(tokens.size() == 0){
			GameScreen.endgame(ID);
		}
	}
	
	public String getTokenPfad(){
		return pfad;
	}
	public int getRemainingTokenCount(){
		return tokens.size();
	}

	public boolean getPlayerCanMove(){
		return playercanMove;
	}
	
	public void setPlayerCanMove(boolean canMove){
		playercanMove = canMove;
	}
	public ArrayList<Token> getRemainingToken(){
		return tokens;
	}
	
	public int getID(){
		return ID;
	}
}
