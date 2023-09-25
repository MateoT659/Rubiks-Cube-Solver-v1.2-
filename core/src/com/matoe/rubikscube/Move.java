package com.matoe.rubikscube;

public enum Move {
    U(0), U2(0), UPRIME(0), D(1), D2(1), DPRIME(1),
    F(2), F2(2), FPRIME(2), B(3), B2(3), BPRIME(3),
    R(4), R2(4), RPRIME(4), L(5), L2(5), LPRIME(5),
//family/2 can represent axis


    M(6), M2(6), MPRIME(6), E(6), E2(6), EPRIME(6),
    S(7), S2(7), SPRIME(7), x(8), x2(8), xPRIME(8),
    y(9), y2(9), yPRIME(9), z(10), z2(10), zPRIME(10),
    r(10), r2(10), rPRIME(10), l(11), l2(11), lPRIME(11),
    u(12), u2(12), uPRIME(12), d(13), d2(13), dPRIME(13),
    f(14), f2(14), fPRIME(14), b(15), b2(15), bPRIME(15);

    public final int family;
    Move(int family){
        this.family = family;
    }
    public static final Move[] expansionMoves = {F, FPRIME, F2, B, BPRIME, B2, R, RPRIME, R2, L, L2, LPRIME, U, U2, UPRIME, D, DPRIME, D2};
    public static final Move[] stage2Moves = {F2, B2, R2, L2, U, U2, UPRIME, D, DPRIME, D2};
    public static Move getInverse(Move m){
        switch(m){
            case R:
                return RPRIME;
            case L:
                return LPRIME;
            case U:
                return UPRIME;
            case D:
                return DPRIME;
            case F:
                return FPRIME;
            case B:
                return BPRIME;
            case RPRIME:
                return R;
            case LPRIME:
                return L;
            case UPRIME:
                return U;
            case DPRIME:
                return D;
            case FPRIME:
                return F;
            case BPRIME:
                return B;
            default:
                return m;
        }
    }

}
