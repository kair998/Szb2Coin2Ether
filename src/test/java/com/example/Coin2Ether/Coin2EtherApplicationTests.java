package com.example.Coin2Ether;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


class Coin2EtherApplicationTests {

	@Test
	public void the2(){
		Coin2Ether.toEther t = new Coin2Ether.toEther(2, 10000);
		int get = t.card2Ether();
		System.out.println(get);
		t.draw.cardStatResult();
		System.out.println(t.draw.statisticCards);
	}

}
