class Cartas:

    def __init__(self,lista):
        self.nome = lista[0]
        self.valor = int(lista[1])
        self.força = float(lista[2])
        self.energia = float(lista[3])
        self.jaken = lista[4]