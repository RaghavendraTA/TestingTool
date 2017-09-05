#include <iostream>
#include <stack>
using namespace std;

bool check_exp(string input) {
    stack<char> st;
    for(char c: input) {
        switch(c) {
            case '{': st.push('}');
                    break;
            case '(': st.push(')');
                    break;
            default:
                if (c == ')' || c == '}') {
                    char t = st.top();
                    st.pop();
                    if (t != c) return false;
                }
                break;
        }
    }
    return true;
}

int main() {
    string input;
    cin >> input;
    if(check_exp(input))
        cout << "Good exp";
    else
        cout << "Bad exp";
    return 0;
}
