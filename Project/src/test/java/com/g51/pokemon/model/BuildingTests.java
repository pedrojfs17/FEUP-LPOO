package com.g51.pokemon.model;

import com.g51.pokemon.model.map.*;
import com.g51.pokemon.model.observer.Observer;
import com.g51.pokemon.model.position.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BuildingTests {
    @Test
    public void BuildingTest(){
        Player player= mock(Player.class);
        Wall wall = mock(Wall.class);
        BuildingDoor buildingDoor = mock(BuildingDoor.class);
        Heal heal = mock(Heal.class);
        Observer observer = mock(Observer.class);
        List<Element> elements =new ArrayList<>();
        elements.add(player);
        elements.add(buildingDoor);
        elements.add(wall);
        elements.add(heal);

        Building building = new Building(player);
        building.addElement(buildingDoor);
        building.addObserver(observer);
        building.addElement(wall);
        building.addElement(heal);

        assertFalse(building.isLeavingBuilding());
        when(buildingDoor.getPosition()).thenReturn(new Position(20,20));
        when(wall.getPosition()).thenReturn(new Position(10,10));
        when(heal.getPosition()).thenReturn(new Position(30,30));
        assertFalse(building.canMove(new Position(10,10)));
        assertTrue(building.canMove(new Position(5,10)));
        building.movePlayerTo(new Position(20,20));
        assertTrue(building.isLeavingBuilding());
        building.changeLeaveBuilding();
        assertFalse(building.isLeavingBuilding());

        assertEquals(elements.size(),building.getAllElements().size());
        verify(observer,times(3)).changed();

        assertFalse(building.isHealingPosition());
        building.movePlayerTo(new Position(30,30));
        assertTrue(building.isHealingPosition());

        assertFalse(building.movePlayerTo(new Position(10, 10)));
        assertTrue(building.movePlayerTo(new Position(11, 11)));
        verify(player, times(1)).setPosition(new Position(11,11));
    }
}
