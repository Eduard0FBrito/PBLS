def criar_binario():
    import pickle
    arq = open("Jogadores_Cadastrados.dat" , "wb")
    dicio_jogadores = {}
    pickle.dump(dicio_jogadores,arq)

criar_binario()
print("Criou")