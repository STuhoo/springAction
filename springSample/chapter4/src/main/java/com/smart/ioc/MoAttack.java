package com.smart.ioc;

public class MoAttack implements ActorArrangable {
	private GeLi geli;

	public void injectGeli(GeLi geli) {
		this.geli = geli;		
	}
	
	public void cityGateAsk() {
		geli.responseAsk("墨者革离");
	}

	//下面代码由 moAttack.xml中 p:geli-ref="geli" 自动产生
	public void setGeli(LiuDeHua geli) {
	}
}