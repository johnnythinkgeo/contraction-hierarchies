package uk.me.mjt.ch;

import uk.me.mjt.ch.Dijkstra.Direction;
import java.io.*;
import java.util.*;
import java.util.zip.GZIPOutputStream;
import static uk.me.mjt.ch.Dijkstra.dijkstrasAlgorithm;
import uk.me.mjt.ch.cache.CachedContractedDijkstra;
import uk.me.mjt.ch.cache.SimpleCache;
import uk.me.mjt.ch.loader.BinaryFormat;
import uk.me.mjt.ch.loader.NodeLoadCsv;

public class LoadAndPathUk {
    
    private static final String expectedPath = "253199386,1424202414,1505184572,1196645367,1505184586,1505184560,32960398,674953,1543269559,674951,247514,674977,247515,29558280,675152,675153,2268308559,196977,196973,1606964593,282635281,675155,1606965958,32951997,32950653,1685715551,243281836,33037832,618353583,618353563,33037912,674983,21303027,196956,1535605749,12896897,1535605760,195820,195502,195466,195468,195469,195470,2453532371,195476,195477,114375381,1506821608,1506821607,534894733,534894719,195835,195833,2285931721,2285931700,196902,2285931666,767862382,196895,196894,60150343,196893,196892,196891,21264448,196890,1628951789,196889,1628948755,1628948796,1628948722,1601410183,1628948764,196886,1628910725,1600455407,247953992,1601289942,268411112,196885,196884,26393798,1600452936,1601181114,196882,196881,1600400548,195455,1634375621,195453,1030659633,195439,195438,3175557833,3175557830,1030659628,195436,26349924,573765,1600106306,195441,573764,195435,49312205,195434,3022609790,1601357145,2013494721,2013494713,2013494719,199453,1461424698,195395,195402,195384,195401,195409,1030640541,195410,195411,195413,195414,195432,26492808,195387,195433,2218950096,1853111633,1663328746,195838,276887,195839,884250392,1663356247,1663352231,1663400203,195849,1663386752,195850,451638,199878,582492979,1663461749,1663421251,1131002278,1663475470,1663475339,1663426986,480041,1663432888,16385387,1663442661,195854,1663451615,1030634572,1030634588,1701556095,1701556071,579493389,195855,245009257,196019,1701653559,1701653563,196020,1030634581,196021,196023,196024,254594086,196026,2643036,196033,196034,196035,1986996234,1677085106,902515750,1677092934,1677092762,579493410,902515739,1677146686,196038,1677107122,196039,587648043,196049,1030634577,196050,902515655,196051,1030634582,196052,60441682,196053,902515758,196054,489665,196055,1030634587,196056,196059,2919813,451680,196060,196061,196062,196064,2121705351,196065,2264206493,196066,2264206502,196067,196073,884288397,2121745158,196079,1984690595,196080,878468016,196081,878468026,196082,196275,277494,196276,56432515,195826,56431981,2121765453,60425389,56442942,195824,196293,1451248468,196295,196296,1978230469,254590925,21575227,21575244,196301,769287498,196302,1978277685,198767,831101857,1978723923,198766,1978310110,1978310081,1983204299,198764,769288602,198763,295272352,1979650590,21762884,21762885,198751,198749,198748,198747,198746,21574700,21574705,198745,198744,84512088,198761,1753861288,179007015,198760,269036818,198758,152286,868249223,868249295,18161381,18161382,868249413,868249381,868249429,868249440,26431433,18161391,868249483,2323254523,2323254519,1440487553,2323781078,2323781071,2323254492,2323254486,2323254482,2323254465,620787167,868249638,152274,26619168,26619169,769267154,620787151,769267156,201646,306473556,868249790,868249730,25497879,152267,152272,199600,98160784,1453648462,25525878,771265117,910343189,25525881,1451319675,26624720,1451325095,1451327176,1451327252,195734,1451327187,2266236805,195727,25416231,248229962,195726,25415499,25415504,25416224,248036773,25416216,25416208,195717,25588278,242681831,1247862079,25878771,2126811776,3376383732,25453971,3376383726,1028854027,195713,25878794,25453975,3377329299,198174,2126811765,2330051152,198156,248522867,198178,198179,198137,631446691,2449187626,198139,2449187622,198142,469434143,168088,33767398,2453040848,2453040849,2453040850,168083,171963413,168082,699975453,2453043228,26243205,313181169,1243387959,2453043237,156621578,2453044353,313181171,87531072,156622240,425720772,313181174,2453044359,168077,199308187,33767403,168078,33767402,168064,376888845,2114879334,2114879337,166450457,23053268,23053270,156665694,427571082,427571070,427571072,427571074,428192385,1145118129,194255,194257,2077157708,337931610,107889,337878826,107890,10703277,2362119846,789567606,1658946728,107891,2682477675,1158837766,16153710,25456191,1159540821,1159556663,2904256672,25473028,2280838947,2280838950,10703451,109359,107893,1953096786,107894,506861368,418036921,107899,1677474812,1158176375,107900,18670880,237900,18670881,18670884, Duration 2345.15 secs (39.09 mins)";
    
    public static void main(String[] args) {
        
        try {
            
            System.out.println("Loading data...");
            long startTime = System.currentTimeMillis();
            BinaryFormat bf = new BinaryFormat();
            HashMap<Long,Node> allNodes=bf.read("/home/mtandy/Documents/contraction hierarchies/binary-test/great-britain-new-contracted-nodes.dat",
                    "/home/mtandy/Documents/contraction hierarchies/binary-test/great-britain-new-contracted-ways.dat");
            Node startNode = allNodes.get(253199386L); // https://www.openstreetmap.org/node/253199386 Hatfield
            //Node startNode = allNodes.get(60455099L); // https://www.openstreetmap.org/node/60455099 Albert Drive, Glasgow
            Node endNode = allNodes.get(18670884L); // https://www.openstreetmap.org/node/18670884 Herbal Hill
            System.out.println("Data load complete in " + (System.currentTimeMillis()-startTime) + "ms.");
            
            DijkstraSolution contracted = ContractedDijkstra.contractedGraphDijkstra(allNodes, startNode, endNode);
            System.out.println("Contraction: "+contracted);
            System.out.println(GeoJson.solution(contracted));
            
            printGraphStats(allNodes);
            
            if (!expectedPath.equals(contracted.toString())) {
                System.exit(-1);
            }
            
            /*// Serialisation size tests:
            int j = 0;
            for (Node n : allNodes.values()) {
                List<DijkstraSolution> upwardSolutions = dijkstrasAlgorithm(allNodes, n, null, Float.POSITIVE_INFINITY, Direction.FORWARDS);
                byte[] serialisedUp = serialiseUpwardSolution(upwardSolutions);
                List<DijkstraSolution> downwardSolutions = dijkstrasAlgorithm(allNodes, n, null, Float.POSITIVE_INFINITY, Direction.BACKWARDS);
                byte[] serialisedDown = serialiseUpwardSolution(downwardSolutions);
                
                System.out.println(n.nodeId + "," + upwardSolutions.size() + "," + serialisedUp.length + "," + downwardSolutions.size() + "," + serialisedDown.length);
                
                if (j++ >= 200) break;
            }*/
            /*
            for (int i=0 ; i<1000 ; i++) {
                contracted = Dijkstra.contractedGraphDijkstra(allNodes, startNode, endNode);
            }
            System.out.println("Warmup completed.");
            
            startTime = System.currentTimeMillis();
            for (int i=0 ; i<10000 ; i++) {
                contracted = Dijkstra.contractedGraphDijkstra(allNodes, startNode, endNode);
            }
            System.out.println("Repeated forwards search in " + (System.currentTimeMillis()-startTime) + "ms.");
            */
            
            System.out.println("Testing speed with caching...");
            
            List<Node> testLocations = chooseRandomNodes(allNodes,4000);
            
            SimpleCache cache = new SimpleCache();
            for (int i=0 ; i<testLocations.size()-1 ; i++) {
                contracted = CachedContractedDijkstra.contractedGraphDijkstra(allNodes, testLocations.get(i), testLocations.get(i+1), cache);
                if (contracted==null) {
                    Node from = testLocations.get(i);
                    Node to = testLocations.get(i+1);
                    System.out.println("No merge found for " + from + " to " + to + ". " + 
                            String.format("http://www.openstreetmap.org/directions?engine=osrm_car&route=%.6f%%2C%.6f%%3B%.6f%%2C%.6f", from.lat,from.lon,to.lat,to.lon));
                }
            }
            System.gc();
            for (int i=0 ; i<testLocations.size()-1 ; i++) {
                contracted = CachedContractedDijkstra.contractedGraphDijkstra(allNodes, testLocations.get(i), testLocations.get(i+1), cache);
            }            
            System.out.println("Warmup/cache completed.");
            
            
            startTime = System.currentTimeMillis();
            for (int j=0 ; j<100 ; j++) {
                for (int i=0 ; i<testLocations.size()-1 ; i++) {
                    contracted = CachedContractedDijkstra.contractedGraphDijkstra(allNodes, testLocations.get(i), testLocations.get(i+1), cache);
                }
            }
            System.out.println("Repeated cached search in " + (System.currentTimeMillis()-startTime) + "ms.");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    private static List<Node> chooseRandomNodes(HashMap<Long,Node> allNodes, int howMany) {
        ArrayList<Node> n = new ArrayList<>(allNodes.values());
        Collections.shuffle(n, new Random(12345));
        return new ArrayList(n.subList(0, howMany));
    }
    
    private static byte[] serialiseUpwardSolution(List<DijkstraSolution> upwardSolutions) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(2500);
            DataOutputStream dos = new DataOutputStream(new GZIPOutputStream(baos));
            HashSet<Node> alreadySerialised = new HashSet<>();
            
            Node startNode = upwardSolutions.get(0).getFirstNode();
            dos.writeLong(startNode.nodeId);
            dos.writeInt(upwardSolutions.size());

            for (DijkstraSolution ds : upwardSolutions) {
                Node destination = ds.getLastNode();
                Node deltaFrom = null;
                if (ds.getPreceding() != null) {
                    deltaFrom = ds.getPreceding().getLastNode();
                }
                if (deltaFrom!= null && !alreadySerialised.contains(deltaFrom)) {
                    throw new RuntimeException("Unexpectedly found solutions were out of order?");
                }

                List<DirectedEdge> directedEdges = ds.getDeltaEdges();
                
                if (directedEdges.size() == 1) {
                    dos.writeLong(directedEdges.get(0).edgeId);
                } else if (destination.equals(startNode) && directedEdges.isEmpty()) {
                    dos.writeLong(-1);
                } else {
                    throw new RuntimeException("Delta edge length isn't 1?");
                }
                
                //dos.writeInt(directedEdges.size());
                //for (DirectedEdge de : directedEdges) {
                //    dos.writeLong(de.edgeId);
                //}
                
                alreadySerialised.add(destination);
            }
            
            dos.close();
            return baos.toByteArray();
        
        } catch (IOException e) {
            throw new RuntimeException("This shouldn't be possible.", e);
        }
    }
    
    private static void printGraphStats(HashMap<Long,Node> allNodes) {
        int nodeCount = 0;
        int nonShortcutEdgeCount = 0;
        int shortcutEdgeCount = 0;
        int maxContractionDepth = 0;
        
        for (Long nodeId : allNodes.keySet()) {
            Node node = allNodes.get(nodeId);
            
            nodeCount++;
            
            for (DirectedEdge de : node.edgesFrom) {
                if (de.isShortcut()) {
                    shortcutEdgeCount++;
                } else {
                    nonShortcutEdgeCount++;
                }
                maxContractionDepth = Math.max(maxContractionDepth, de.contractionDepth);
            }
        }
        
        System.out.println("nodeCount: "+nodeCount);
        System.out.println("nonShortcutEdgeCount: "+nonShortcutEdgeCount);
        System.out.println("shortcutEdgeCount: "+shortcutEdgeCount);
        System.out.println("maxContractionDepth: "+maxContractionDepth);
    }
    
}
