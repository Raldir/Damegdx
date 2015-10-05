package com.mygdx.dame;


public class DameToken extends Token {

	public DameToken(String pfad, AbstractAsset as, Player p) {
		super(pfad, as, p);
		p.addToken(this);
	}

}
