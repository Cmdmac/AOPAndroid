package org.cmdmac.aopplugin

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory

class ListExtention {
  def String name = 'def'
  def String msg
  def Options options;
  public ListExtention(ObjectFactory objectFactory) {
    options = objectFactory.newInstance(Options)
  }
//
  public ListExtention(String name) {
    this.name = name
  }

  def name(String n){
    this.name = n;
  }

  def getName() {
    return this.name;
  }

  def msg(String m) {
    this.msg = m;
  }

  void options(Action<? super Options> action) {
    action.execute(options)
  }

}