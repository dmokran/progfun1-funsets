type Set = Int => Boolean
def contains(s: Set, elem: Int): Boolean = s(elem)

def singletonSet(elem: Int): Set = x => x == elem
val ss1 = singletonSet(1)
val ss2 = singletonSet(2)
val ss3 = singletonSet(3)
val ss4 = singletonSet(4)
val ss5 = singletonSet(5)
val ss6 = singletonSet(6)

ss3(3) | ss2(2)

contains(ss2, 2)

def union(s: Set, t: Set): Set = x => s(x) | t(x)
def intersect(s: Set, t: Set): Set = x => s(x) & t(x)
def diff(s: Set, t: Set): Set = x => s(x) & !t(x)
def filter(s: Set, p: Int => Boolean): Set = x => contains(s, x) & p(x)



val ss23 = union(ss2, ss3)
val ss234 = union(ss23, ss4)

val ss34 = union(ss3, ss4)
val ss345 = union(ss34, ss5)

val ss45 = union(ss4, ss5)
val ss456 = union(ss45, ss6)

val in234_345 = intersect(ss234, ss345)
in234_345(3)

val in234_456 = intersect(ss234, ss456)
in234_456(4)

val di234_345 = diff(ss234, ss345)
di234_345(5)

val fil3 = filter(ss234, x => x != 3)
fil3(4)
