package com.dreamblitz.webflux17.common.utils;

import reactor.core.publisher.BaseSubscriber;

public class CustomSubscriber extends BaseSubscriber<Integer> {

  int count = 0 ;

  @Override
  protected void hookOnNext(Integer value) {
      //request(3);
      count++;
      System.out.println(value);
      if(count ==5) {
          cancel();
      }

  }

  public static BaseSubscriber<Integer> getSubSubscriber() {
      return new CustomSubscriber();
  }
}
