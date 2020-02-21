package com.Map;

import com.Heroes.Hero;

import java.util.List;

public class MapCell {
    private LandTypes landType;
    private List<Hero> heroesList;

    MapCell(final char landType) {
        setLandType(landType);
        heroesList = null;
    }

    private void setLandType(final char landType) {
        switch (landType) {
            case 'D':
                this.landType = LandTypes.Desert;
                break;
            case 'L':
                this.landType = LandTypes.Land;
                break;
            case 'V':
                this.landType = LandTypes.Volcanic;
                break;
            case 'W':
                this.landType = LandTypes.Woods;
                break;
            default:
                break;
        }
    }

    public final LandTypes getLandType() {
        return landType;
    }

    public final List<Hero> getHeroesList() {
        return heroesList;
    }

    public final void setHeroesList(final List<Hero> heroesList) {
        this.heroesList = heroesList;
    }
}
