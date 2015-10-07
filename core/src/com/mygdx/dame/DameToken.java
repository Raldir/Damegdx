package com.mygdx.dame;

import java.util.ArrayList;


public class DameToken extends Token {

	public DameToken(String pfad, Asset as, Player p) {
		super(pfad, as, p);
		p.addToken(this);
	}
	
	@Override
	public void addTarget(final ArrayList<Asset> assets, final int target, Player p){
		
	}
	
	@Override
	public void setGenerellMovement(Player p, final ArrayList<Asset> assets){
		
		
	}
	
	@Override
	public void movement(ArrayList<Asset> assets){
		
		
	}
}
