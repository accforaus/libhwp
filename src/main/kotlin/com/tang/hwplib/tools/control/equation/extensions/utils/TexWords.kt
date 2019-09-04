package com.tang.hwplib.tools.control.equation.extensions.utils

const val OPENPARTMP = 1.toByte().toChar()
const val CLOSEPARTMP = 2.toByte().toChar()

val oneParameter = arrayListOf(
        "\\sqrt", "\\mrm", "\\vec",
        "\\vrm", "\\overline", "\\ovl",
        "\\begintabu", "\\sqrt", "_",
        "^", "\\begin", "\\end", "\\framebox",
        "\\overleftrightarrow", "\\acute",
        "\\grave", "\\ddot", "\\dot",
        "\\underline", "\\hat", "\\check",
        "\\arc", "\\widetilde"
)

val twoParameter = arrayListOf("\\dfrac")

val needToWrap = arrayListOf(
        "\\overline", "\\vec", "\\framebox"
        ,"\\overleftrightarrow", "\\acute", "\\grave", "\\ddot", "\\dot"
        ,"\\underline", "\\hat", "\\check", "\\arc", "\\widetilde"
)

val spcHwp = arrayListOf("&lt;", "&gt;", "&amp;")
val spcTex = arrayListOf("<", ">", "&")

val funcHwp = arrayListOf(
        "sinh", "cosh", "tanh", "sech", "csch", "cosech", "coth", "arcsin", "arccos", "arctan",
        "csc", "cosec", "sec", "cot", "sin", "cos", "tan", "log", "ln", "lg",
        "max", "min", "arc", "arg", "Exp", "exp", "det", "gcd", "mod",
        "if", "for", "and", "hom", "ker", "deg", "dim", "DEG",
        "APPROX", "SIMEQ", "CONG", "ASYMP", "ISO", "DIAMOND", "DSUM", "FORALL",
        "Partial", "LNOT", "PROPTO", "XOR", "NABLA", "DDAGGER", "DAGGER",
        "CDOTS", "LDOTS", "VDOTS", "DDOTS", "TRIANGLE", "MSANGLE", "SANGLE", "RTANGLE",
        "VDASH", "DASHV", "TOP", "MODELS", "LAPLACE", "CENTIGRADE", "FAHRENHEIT",
        "LSLANT", "RSLANT", "ATT", "HUND", "THOU", "WELL", "BASE", "BENZENE", "SIM", "INF", "INFTY", "rightarrow",
        "BULLET", "AST", "STAR", "BIGCIRC", "THEREFORE", "BECAUSE", "IDENTICAL", "EXIST", "DOTEQ", "image", "REIMAGE",
        "NOTOWNS", "notOWNS", "NOTIN", "OWNS", "SQSUBSETEQ", "SQSUBSET", "UPLUS",
        "SMALLSUM", "SMALLPROD", "SMCOPROD", "SQCAP", "SQCUP", "OPLUS", "OMINUS", "OTIMES", "ODIV", "ODOT", "LOR", "LAND",
        "SUBSETEQ", "SUPSETEQ", "SUBSET", "SUPERSET",
        "larrow", "rarrow", "uparrow", "downarrow", "LARROW", "LRARROW", "DOWNARROW", "UPARROW", "udarrow",
        "UDARROW", "RARROW", "NWARROW", "SEARROW", "NEARROW", "SWARROW",
        "HOOKLEFT", "HOOKRIGHT", "MAPSTO", "PVER",
        "CUP", "CAP", "SMALLINTER", "SMALLUNION", "EMPTYSET", "cdot", "CDOT", "IN", "SUCC", "PREC",
        "ALEPH", "hbar", "imath", "jmath", "Ω", "LITER", "WP", "ANGSTROM",
        "vartheta", "varpi", "varsigma", "varupsilon", "varphi", "varepsilon", "LEFT", "RIGHT", "bar", "vec", "box",
        "dyad", "acute", "grave", "ddot", "dot", "under", "hat", "check", "arch", "tilde", "ANGLE", "rm", "it", "BOT",
        "ALPHA", "BETA", "GAMMA", "DELTA", "EPSILON", "ZETA", "ETA", "THETA",
        "IOTA", "KAPPA", "LAMBDA", "MU", "NU", "XI", "PI", "RHO",
        "SIGMA", "TAU", "UPSILON", "PHI", "CHI", "PSI", "OMEGA",
        "alpha", "beta", "gamma", "delta", "epsilon", "zeta", "eta", "theta",
        "iota", "kappa", "lambda", "mu", "nu", "xi", "pi", "rho",
        "sigma", "tau", "upsilon", "phi", "chi", "psi", "omega",
        "sum", "prod", "coprod", "inter", "union", "BIGSQCAP", "BIGSQCUP",
        "BIGOPLUS", "BIGOMINUS", "BIGOTIMES", "BIGODIV", "BIGODOT", "BIGUPLUS",
        "int", "dint", "tint", "oint", "odint", "otint",
        "leq", "geq", "le", "ge", "sqrt", "root", "lim", "Lim", "over",
        "times", "prime", "circ", "pile", "DIVIDE", "div"
)

val funcTex = arrayListOf(
         "\\sinh", "\\cosh", "\\tanh",
        "\\operatorname{sech}", "\\operatorname{sech}", "\\operatorname{cosech}", "\\operatorname{coth}",
        "\\arcsin", "\\arccos", "\\arctan",
        "\\csc", "\\operatorname{cosec}", "\\sec", "\\cot", "\\sin", "\\cos", "\\tan", "\\log", "\\ln", "\\lg",
        "\\max", "\\min", "\\mrm{arc}", "\\arg", "\\operatorname{Exp}", "\\exp", "\\det", "\\gcd", "\\bmod",
        "\\mrm{if}", "\\mrm{for}", "\\mrm{and}", "\\hom", "\\ker", "\\deg", "\\dim", "^{\\circ}",
        "\\approx", "\\simeq", "\\cong", "\\asymp", "\\Bumpeq", "\\diamond", "\\dotplus", "\\forall",
        "\\partial", "\\lnot", "\\propto", "\\veebar", "\\nabla", "\\ddagger", "\\dagger",
        "\\cdots", "\\ldots", "\\vdots", "\\ddots", "\\triangle", "\\measuredangle", "\\sphericalangle", "rtangle",
        "\\vdash", "\\dashv", "\\top", "\\models", "\\laplace", "\\centigrade", "\\fahrenhe",
        "\\diagup", "\\diagdown", "att", "hund", "thou", "well", "base", "benzene",
        "\\sim", "\\infty", "\\infty", "\\to",
        "\\bullet", "\\ast", "\\star", "\\bigcirc", "\\therefore", "\\because", "identical", "\\exists", "\\doteq", "\\fallingdotseq", "\\risingdotseq",
        "\\not\\ni", "\\not\\ni", "\\notin", "\\ni", "\\sqsubseteq", "\\sqsubset", "\\uplus",
        "\\Sigma", "\\Pi", "\\amalg", "\\sqcap", "\\sqcup",
        "\\oplus", "\\ominus", "\\otimes", "\\oslash", "\\odot", "\\lor", "\\land",
        "\\subseteq", "\\supseteq", "\\subset", "\\supset",
        "\\leftarrow", "\\rightarrow", "\\uparrow", "\\downarrow", "\\Leftarrow", "\\Leftrightarrow", "\\Downarrow", "\\Uparrow", "\\updownarrow",
        "\\Updownarrow", "\\Rightarrow", "\\nwarrow", "\\searrow", "\\nearrow", "\\swarrow",
        "\\hookleftarrow", "\\hookrightarrow", "\\mapsto", "\\Vert",
        "\\cup", "\\cap", "\\cap", "\\cup", "\\varnothing", "\\bcd", "\\bcd", "\\in", ">", "<",
        "\\aleph", "\\hbar", "\\imath", "\\jmath", "\\Omega", "\\ell", "\\wp", "ANGSTROM",
        "\\vartheta", "\\varpi", "\\varsigma", "\\varupsilon", "\\varphi", "\\varepsilon", "\\left", "\\right",
        "\\overline", "\\vec", " ",
        "\\overleftrightarrow", "\\acute", "\\grave", "\\ddot", "\\dot", "\\underline", "\\hat", "\\check", "\\arc", "\\widetilde",
        "\\angle", "\\rm", "\\it", "\\bot",
        "A", "B", "\\Gamma", "\\Delta", "E", "Z", "H", "\\Theta",
        "I", "K", "\\Lambda", "M", "N", "\\Xi", "\\Pi", "P",
        "\\Sigma", "T", "Y", "\\Phi", "X", "\\Psi", "\\Omega",
        "\\alpha", "\\beta", "\\gamma", "\\delta", "\\epsilon", "\\zeta", "\\eta", "\\theta",
        "\\iota", "\\kappa", "\\lambda", "\\mu", "\\nu", "\\xi", "\\pi", "\\rho",
        "\\sigma", "\\tau", "\\upsilon", "\\phi", "\\chi", "\\psi", "\\omega",
        "\\sum", "\\prod", "\\coprod", "\\bigcap", "\\bigcup", "\\bigsqcap", "\\bigsqcup",
        "\\bigoplus", "BIGOMINUS", "\\bigotimes", "BIGODIV", "\\bigodot", "\\biguplus",
        "\\int", "\\iint", "\\iiint", "\\oint", "oiint", "oiiint",
        "\\le", "\\ge", "\\le", "\\ge",
        "\\sqrt", "\\sqrt", "\\lim", "\\operatorname{Lim}", "\\kfrac",
        "\\times", "'", "\\circ", "\\pile", "\\div", "\\div"
)

val spcHwpNext = arrayListOf(
        ",", "%", "-+", "!=", "<->", "->", "<<<", ">>>", "<<", ">>", "==", "<=", ">=", "+-", "-+"
)

val spcTexNext = arrayListOf(
        ",\\: ", "\\% ", "\\mp ", "\\ne ", "\\leftrightarrow ", "\\to ", "\\lll ", "\\ggg ", "\\ll ", "\\gg ", "\\equiv ", "\\le ", "\\ge ", "\\pm ", "\\mp "
)

val spcUnicode1 = arrayListOf(0xC2.toByte().toChar(), 0xB1.toByte().toChar(), 0x00.toByte().toChar())
val spcUnicode2 = arrayListOf(0xC3.toByte().toChar(), 0xB7.toByte().toChar(), 0x00.toByte().toChar())
val spcUnicode3 = arrayListOf(0xE2.toByte().toChar(), 0x8A.toByte().toChar(), 0x92.toByte().toChar(), 0x00.toByte().toChar())
val spcUnicode4 = arrayListOf(0xE2.toByte().toChar(), 0x8A.toByte().toChar(), 0x90.toByte().toChar(), 0x00.toByte().toChar())
val spcUnicode5 = arrayListOf(0xEE.toByte().toChar(), 0x81.toByte().toChar(), 0x8D.toByte().toChar(), 0x00.toByte().toChar())
val spcUnicode6 = arrayListOf(0xC2.toByte().toChar(), 0xB0.toByte().toChar(), 0x0.toByte().toChar())
val spcUnicode7 = arrayListOf(0xE2.toByte().toChar(), 0x88.toByte().toChar(), 0xA0.toByte().toChar(), 0x00.toByte().toChar())

val hwpUnicodeSpc = arrayListOf(spcUnicode1, spcUnicode2, spcUnicode3, spcUnicode4, spcUnicode5, spcUnicode6, spcUnicode7)
val texUnicodeSpc = arrayListOf("\\pm ", "\\div ", "\\sqsupseteq ", "\\sqsupset ", "\\vert ", "^{\\circ}", "\\angle ")

val hwpUnicodeSpcLen = arrayListOf(2, 2, 3, 3, 3, 2, 3)
val texUnicodeSpcLen = arrayListOf(4, 5, 12, 10, 6, 8, 7)

val funcRemain = arrayListOf(
         "\\xrm", "\\beginttabuu", "\\laplace", "\\centigrade", "\\fahrenhe",
        "$ ", " $", "{ ", " {", " }", "} ", "( ", " (", " )", ") ", "[ ", " [", " ]", "] ",
        " \\", " '", "' ", " ^", "^ ", " _", "_ ", "\\mrm{}", "#", "\\it", "~", "\n ", "\n\n"
)
val funcRemainNext = arrayListOf(
        "\\mrm", "\\begin{tabu}", "\\mathcal{ }", "^{\\circ}\\mrm{C}", "{}^{\\circ}\\mrm{F}",
        "$", "$", "{", "{", "}", "}", "(", "(", ")", ")", "[", "[", "]", "]",
        "\\", "'", "'", "^", "^", "_", "_", "", "\\\\", "", "\\;", "\n", "\n"
)

val parameterWrap = arrayListOf(
        "sinh", "cosh", "tanh", "sech", "csch", "cosech", "coth",
        "arcsin", "arccos", "arctan",
        "csc", "cosec", "sec", "cot", "sin", "cos", "tan", "log", "ln", "lg",
        "max", "min", "arg", "Exp", "exp", "gcd", "mod",
        "if", "for", "and", "hom", "ker", "deg", "dim", "NOTOWNS", "notOWNS",
        "LEFT", "RIGHT", "bar", "vec", "box",
        "dyad", "acute", "grave", "ddot", "dot", "under", "hat", "check", "arch", "tilde",
        "rm", "it", "sum", "prod", "coprod", "inter", "union", "BIGSQCAP", "BIGSQCUP",
        "BIGOP US", "BIGOMINUS", "BIGOTIMES", "BIGODIV", "BIGODOT", "BIGUPLUS",
        "int", "dint", "tint", "oint", "odint", "otint",
        "sqrt", "root", "lim", "Lim", "over", "pile"
)

val doubleSubSupList = arrayListOf(
        "\\sum", "\\prod", "\\int", "\\iint", "\\iiint", "\\oint",
        "\\bigcup", "\\bigcap",  "\\bigsqcup",  "\\biguplus",
        "\\bigvee  \\bigwedge  \\bigoplus", " \\bigotimes", " \\bigodot"
)