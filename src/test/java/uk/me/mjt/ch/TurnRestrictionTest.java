
package uk.me.mjt.ch;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class TurnRestrictionTest {

    public TurnRestrictionTest() {
    }

    @Test
    public void testAdjustGraphSingleNodeTurnRestriction() {
        System.out.println("testAdjustGraphSingleNodeTurnRestriction");
        
        MapData allNodes = MakeTestData.makeTurnRestrictedH();
        TurnRestriction.adjustGraphToImplementTurnRestrictions(allNodes);
        
        System.out.println("PUML for post-modification graph: " + Puml.forNodes(allNodes.getAllNodes()));
        
        Node startNode = allNodes.getNodeById(3L);
        Node endNode = allNodes.getNodeById(6L);
        
        DijkstraSolution ds = Dijkstra.dijkstrasAlgorithm(startNode, endNode, Dijkstra.Direction.FORWARDS);
        System.out.println("Solution: "+ds);
        assertEquals(5000, ds.totalDriveTimeMs);
    }
    
    @Test
    public void testAdjustGraphMultiNodeTurnRestriction() {
        System.out.println("testAdjustGraphMultiNodeTurnRestriction");
        
        MapData allNodes = MakeTestData.makeTurnRestrictedA();
        TurnRestriction.adjustGraphToImplementTurnRestrictions(allNodes);
        
        System.out.println("PUML for post-modification graph: " + Puml.forNodes(allNodes.getAllNodes()));
        
        Node startNode = allNodes.getNodeById(3L);
        Node endNode = allNodes.getNodeById(6L);
        
        DijkstraSolution ds = Dijkstra.dijkstrasAlgorithm(startNode, endNode, Dijkstra.Direction.FORWARDS);
        System.out.println("Solution: "+ds);
        assertEquals(5000, ds.totalDriveTimeMs);
    }
    
    @Test
    public void testTurnRestrictionDoesntBreakStraightOn() {
        System.out.println("testTurnRestrictionDoesntBreakStraightOn");
        // REVISIT is there an analogous example with an -ONLY restriction?
        
        MapData allNodes = MakeTestData.makeTurnRestrictedThorn();
        System.out.println("PUML for pre-modification graph: " + Puml.forNodes(allNodes.getAllNodes()));
        TurnRestriction.adjustGraphToImplementTurnRestrictions(allNodes);
        System.out.println("PUML for post-modification graph: " + Puml.forNodes(allNodes.getAllNodes()));
        
        Node startNode = allNodes.getNodeById(1L);
        Node endNode = allNodes.getNodeById(4L);
        
        DijkstraSolution ds = Dijkstra.dijkstrasAlgorithm(startNode, endNode, Dijkstra.Direction.FORWARDS);
        System.out.println("Solution: "+ds);
        assertEquals(3000, ds.totalDriveTimeMs);
    }
}