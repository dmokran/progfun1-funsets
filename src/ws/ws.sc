type Set = Int => Boolean
def contains(s: Set, elem: Int): Boolean = s(elem)

contains(Set(1), 2)

def singletonSet(elem: Int): Set = x => x == elem
val ss2 = singletonSet(2)
val ss3 = singletonSet(3)

ss3(3) | ss2(2)

contains(ss2, 2)

def union(s: Set, t: Set): Set = x => s(x) | t(x)
def intersect(s: Set, t: Set): Set = x => s(x) & t(x)
def diff(s: Set, t: Set): Set = x => s(x) & !t(x)

val un2_3 = union(ss2, ss3)
un2_3(4)

val in2_3 = intersect(ss2, ss2)
in2_3(2)

val di2_3 = diff(ss2, ss3)
di2_3(2)
