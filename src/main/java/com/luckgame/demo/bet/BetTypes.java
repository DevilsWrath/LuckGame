package com.luckgame.demo.bet;

import java.util.ArrayList;
import java.util.List;

public enum BetTypes {
    HOME,
    DRAW,
    AWAY,
    NONE;

    public static List<BetTypes> valueOf() {
        List<BetTypes> betTypes = new ArrayList<>();
        betTypes.add(HOME);
        betTypes.add(DRAW);
        betTypes.add(AWAY);
        return betTypes;
    }
}
