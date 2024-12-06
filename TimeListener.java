/**
 * File: TimeListener.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: provide a callback mechanism  for handling time-based events. 
 * 			It allows objects to receive updates about elapsed time.
 */

public interface TimeListener {
    void onTimeUpdate(long elapsedTime);
}
