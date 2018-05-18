package org.cmdmac.aopplugin

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory;

class OptionsExtentions {
    def String xxx;
    def String yyy;
    def Options options;
//    @javax.inject.Inject
    public OptionsExtentions(ObjectFactory objectFactory) {
        options = objectFactory.newInstance(Options)
    }
//
//    def xxx(String v) {
//        xxx = v;
//    }
//    public void setxxx(String v) {
//        xxx = v;
//    }
//
//    public String getXxx() {
//        return xxx;
//    }

    void options(Action<? super Options> action) {
        action.execute(options)
    }
}
