
# Sobre atributos de tags:

    ## valores literais

Integer, Byte, Short, Long:     ^-?\d+$                       ex: 1, 0, 45, -32131231

Float, Double:                  ^-?((\d*(\.\d+)?)|(\.\d+))$   ex: 1, 1.0, 12321.0, 12332.13213, -0.13123, -.21323

Boolean:                        ^true|false$ [CI]             ex: true, false, TrUe, FALSE

Char:                           ^.{1}$                        ex: a, B, 4, %, =

String:                         ^(.*)$                        ex: dsadsa, 45s4ad56m, s*%d*d%a&%s*%Da*DD56

List<?>:                        ^<<type>>(,<<type>>)*$        ex: 1, 2, 3, 4 -> List<Integer>

O valor literal de array é substituído pelo objeto List.

Objetos complexos devem ser passados por expressão

    ## syntax de expressão: {{ <identificador> }}

@  <<identificador>>        : props

$  <<identificador>>        : states

#  <<identificador>>        : método da classe correspondente

   <<identificador>>        : propriedade da classe correspondente

:: <<identificador>>        : variavel de iteração

.  <<identificador>>        : acesso em objetos

{{ @material.name }}

{{ #getVersion }}

<foreach list="{{ @materials }}" item="material" >
    <button> {{ :: material.name }} </button>
</foreach>

