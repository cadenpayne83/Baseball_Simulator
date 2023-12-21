//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.google.common.truth.Truth.assertThat;
//import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
//public class HitTest {
//   @Test
//    public void testHits() {
//       Team team = new Team("Test Team");
//       Player player = new Player(0.250, team);
//       int outs = 0;
//       int singles = 0;
//       int doubles = 0;
//       int triples = 0;
//       int homeRuns = 0;
//       int atBats = 0;
//       for (int i = 0; i < 100; i++) {
//           int bases = player.hit();
//           atBats += 1;
//           if (bases == 0) {
//               outs += 1;
//           } else if (bases == 1) {
//               singles += 1;
//           } else if (bases == 2) {
//               doubles += 1;
//           } else if (bases == 3) {
//               triples += 1;
//           } else if (bases == 4) {
//               homeRuns += 1;
//           }
//       }
//
//       System.out.println("Player had " + atBats + " at bats, got out " + outs + " times, had " + singles + " singles, "
//               + doubles + " doubles, " + triples + " triples, and " + homeRuns + " home runs.");
//   }
//}
