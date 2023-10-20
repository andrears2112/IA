package TicTacToe;

class ComputerPlayer {
    private char botPlayer;
    private char humanPlayer;

    public ComputerPlayer(char letter) {
        botPlayer = letter;
        humanPlayer = (letter == 'O') ? 'X' : 'O';
    }

    public char players(char[] state) {
        int x = 0;
        int o = 0;
        for (int i = 0; i < 9; i++) {
            if (state[i] == 'X') {
                x++;
            }
            if (state[i] == 'O') {
                o++;
            }
        }
        if (humanPlayer == 'X') {
            return (x == o) ? 'X' : 'O';
        }
        if (humanPlayer == 'O') {
            return (x == o) ? 'O' : 'X';
        }
        return '-';
    }

    public int[] actions(char[] state) {
        int count = 0;
        for (char c : state) {
            if (c == '-') {
                count++;
            }
        }
        int[] actions = new int[count];
        int index = 0;
        for (int i = 0; i < 9; i++) {
            if (state[i] == '-') {
                actions[index] = i;
                index++;
            }
        }
        return actions;
    }

    public char[] result(char[] state, int action) {
        char[] newState = state.clone();
        char player = players(state);
        newState[action] = player;
        return newState;
    }

    public boolean terminal(char[] state) {
        if (isPlayerWin(state, 'X')) {
            return true;
        }
        if (isPlayerWin(state, 'O')) {
            return true;
        }
        return false;
    }

    public boolean isPlayerWin(char[] state, char player) {
        if (state[0] == player && state[1] == player && state[2] == player) {
            return true;
        }
        if (state[3] == player && state[4] == player && state[5] == player) {
            return true;
        }
        if (state[6] == player && state[7] == player && state[8] == player) {
            return true;
        }
        if (state[0] == player && state[3] == player && state[6] == player) {
            return true;
        }
        if (state[1] == player && state[4] == player && state[7] == player) {
            return true;
        }
        if (state[2] == player && state[5] == player && state[8] == player) {
            return true;
        }
        if (state[0] == player && state[4] == player && state[8] == player) {
            return true;
        }
        if (state[2] == player && state[4] == player && state[6] == player) {
            return true;
        }
        return false;
    }

    public int minimax(char[] state, char player) {
        char maxPlayer = humanPlayer;
        char otherPlayer = (player == 'X') ? 'O' : 'X';
        if (terminal(state)) {
            return (otherPlayer == maxPlayer) ? 1 * (actions(state).length + 1) : -1 * (actions(state).length + 1);
        } else if (isBoardFilled(state)) {
            return 0;
        }
        if (player == maxPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (int possibleMove : actions(state)) {
                char[] newState = result(state, possibleMove);
                int simScore = minimax(newState, otherPlayer);
                if (simScore > bestScore) {
                    bestScore = simScore;
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int possibleMove : actions(state)) {
                char[] newState = result(state, possibleMove);
                int simScore = minimax(newState, otherPlayer);
                if (simScore < bestScore) {
                    bestScore = simScore;
                }
            }
            return bestScore;
        }
    }

    public int machineMove(char[] state) {
        int square = -1;
        int bestScore = Integer.MIN_VALUE;
        for (int possibleMove : actions(state)) {
            char[] newState = result(state, possibleMove);
            int simScore = minimax(newState, botPlayer);
            if (simScore > bestScore) {
                bestScore = simScore;
                square = possibleMove;
            }
        }
        return square;
    }

    private boolean isBoardFilled(char[] state) {
        for (char c : state) {
            if (c == '-') {
                return false;
            }
        }
        return true;
    }
}