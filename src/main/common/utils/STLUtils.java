package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class STLUtils {
//    template<class _BidIt> inline
//        bool _Next_permutation(_BidIt _First, _BidIt _Last)
//        {	// permute and test for pure ascending, using operator<
//        _DEBUG_RANGE(_First, _Last);
//        _BidIt _Next = _Last;
//        if (_First == _Last || _First == --_Next)
//            return (false);
//
//        for (; ; )
//            {	// find rightmost element smaller than successor
//            _BidIt _Next1 = _Next;
//            if (_DEBUG_LT(*--_Next, *_Next1))
//                {	// swap with rightmost element that's smaller, flip suffix
//                _BidIt _Mid = _Last;
//                for (; !_DEBUG_LT(*_Next, *--_Mid); )
//                    ;
//                std::iter_swap(_Next, _Mid);
//                std::reverse(_Next1, _Last);
//                return (true);
//                }
//
//            if (_Next == _First)
//                {	// pure descending, flip all
//                std::reverse(_First, _Last);
//                return (false);
//                }
//            }
//        }

    public static boolean next_permutation(long a[], int n) {
        if (n == 0 || n==1) return false;

        int next = n-1;
//        while (a[next-1] < a[next]) {
//
//        }
        for ( ; ; ) {
            // find rightmost element smaller than successor
            int next1 = next;
            if (a[--next] < a[next1]) {
                // swap with rightmost element that's smaller, flip suffix
                int mid = n;
                while (a[next] >= a[--mid]);
                swap(a, next, mid);
                reverse(a, next1, n-1);
                return true;
            }

            if (next == 0) {
                // pure descending, flip all
                reverse( a, 0, n-1 );
                return false;
            }
        }

//        return true;
    }

    private static void swap(long a[], int i, int j) {
        long t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean next_permutation(long a[]) {
        return next_permutation(a, a.length);
    }

    public static boolean next_permutation(int a[], int n) {
        if (n == 0 || n==1) return false;

        int next = n-1;
//        while (a[next-1] < a[next]) {
//
//        }
        for ( ; ; ) {
            // find rightmost element smaller than successor
            int next1 = next;
            if (a[--next] < a[next1]) {
                // swap with rightmost element that's smaller, flip suffix
                int mid = n;
                while (a[next] >= a[--mid]);
                swap(a, next, mid);
                reverse(a, next1, n-1);
                return true;
            }

            if (next == 0) {
                // pure descending, flip all
                reverse( a, 0, n-1 );
                return false;
            }
        }

//        return true;
    }

    private static void swap(int a[], int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean next_permutation(int a[]) {
        return next_permutation(a, a.length);
    }

    public static void reverse(long a[], int b, int e) {
        if (!(b <= e)) {
            throw new IllegalArgumentException("Wrong begin & end");
        }

        for (; b < e; ++b, --e) {
            long t = a[b];
            a[b] = a[e];
            a[e] = t;
        }
    }

    public static void reverse(long a[]) {
        reverse(a, 0, a.length-1);
    }

    public static void reverse(int a[], int b, int e) {
        if (!(b <= e)) {
            throw new IllegalArgumentException("Wrong begin & end");
        }

        for (; b < e; ++b, --e) {
            int t = a[b];
            a[b] = a[e];
            a[e] = t;
        }
    }

    public static void reverse(int a[]) {
        reverse(a, 0, a.length-1);
    }

    public static void reverse(byte a[], int b, int e) {
        if (!(b <= e)) {
            throw new IllegalArgumentException("Wrong begin & end");
        }

        for (; b < e; ++b, --e) {
            byte t = a[b];
            a[b] = a[e];
            a[e] = t;
        }
    }

    public static void reverse(byte a[]) {
        reverse(a, 0, a.length-1);
    }

    public static long accumulate(long[] arr) {
        long res = 0;
        for (long n : arr) {
            res += n;
        }
        return res;
    }

    public static int accumulate(int[] arr) {
        int res = 0;
        for (int n : arr) {
            res += n;
        }
        return res;
    }

    public static <T> List<T> reverse(List<T> list) {
        List<T> nlist = new ArrayList<T>(list);
        Collections.reverse(nlist);
        return nlist;
    }
}
