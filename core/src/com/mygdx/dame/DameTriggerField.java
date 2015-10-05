package com.mygdx.dame;

public class DameTriggerField extends AbstractAsset {
	
	public int iD;
	private String tokenPfad;

	public DameTriggerField(String pfad, float x, float y, float width, float height, int i, String tokenPfad){
		super(pfad, x, y, width, height, i);
		this.tokenPfad = tokenPfad;
		if(i <= 8){
			iD = 1;
		}else{
			iD = 0;
		}
	}
	

	@Override
	public boolean specialEvent() {
		if(token != null && token.getPlayer().ID == iD){
			System.out.println("hello");
			Player tempP = token.getPlayer();
			token.remove();
			token = new DameToken(tokenPfad, this, tempP);
			return true;
		}
		return false;
	}
}
