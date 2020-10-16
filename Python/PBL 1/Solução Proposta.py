import sys
import os
from prettytable import PrettyTable
from prettytable import MSWORD_FRIENDLY

def is_number(num):
    try:
        float(num)
        return True
    except:
        pass
    return False

listaEletro = ["Ar-condicionado", "Computador", "Geladeira", "Lâmpada", "Televisor"]
continuar = True
setorAtual = 0
setorTotal = 0

# Menu
print("[1] Calcular consumo de energia")
print("[2] Sair do programa")
while continuar:
    opcao = input("\nQual sua opção? ")
    while not opcao.isnumeric() or 0 > int(opcao) or int(opcao) > 2:
        print("Opção invalida, tente novamente!")
        opcao = input("\nQual sua opção? ")
    if int(opcao) == 1:
        tarifa = input("Informe a tarifa residencial: ")
        while not is_number(tarifa) or float(tarifa) <= 0:
            print("Valor invalido, tente novamente")
            tarifa = input("Informe a tarifa residencial: ")
        tarifa = float(tarifa)
        calculosOn = True
        while calculosOn:
            setorNome = input("Qual o nome desse setor? ")
            print(f"\n{setorNome}")
            for i in range(5):
                print(f"\nEletronico: {listaEletro[i]}")
                quantAparelho = input("Quantidade no setor: ")
                while not quantAparelho.isnumeric() or int(quantAparelho) < 0:
                    print("Opção invalida, tente novamente!")
                    quantAparelho = input("Quantidade no setor: ")
                quantAparelho = int(quantAparelho)
                if quantAparelho == 0:
                    if i < 4:
                        print(f"Esse setor não possui {listaEletro[i]}, então vamos para o proximo aparelho!")
                    else:
                        print(f"Esse setor não possui {listaEletro[i]}, vamos finalizar os calculos!")
                else:
                    potencia = input("Qual a potencia desse aparelho? ")
                    while not potencia.isnumeric() or int(potencia) <= 0:
                        print("Opção invalida, tente novamente!")
                        potencia = input("Qual a potencia desse aparelho? ")
                    potencia = int(potencia)
                    quantHrs = input("Quantas horas esse aparelho funciona por dia? ")
                    while not quantHrs.isnumeric() or int(quantHrs) < 0 or int(quantHrs) > 24:
                        print("Opção invalida, tente novamente!")
                        quantHrs = input("Quantas horas esse aparelho funciona por dia? ")
                    quantHrs = int(quantHrs)
                    quantDias = input("Quantos dias por mes? ")
                    while not quantDias.isnumeric() or int(quantDias) < 0 or int(quantDias) > 31:
                        print("Opção invalida, tente novamente!")
                        quantDias = input("Quantos dias por mes? ")
                    quantDias = int(quantDias)
                    # Dividido por 1000 para transformar em kWh
                    consumo = (((quantHrs * quantDias) * potencia) * quantAparelho) / 1000
                    setorAtual += tarifa * consumo
            setorTotal += setorAtual
            novaConsulta = False
            while not novaConsulta:
                print("\n[1] - Calcular mais um setor\n[2] - Exibir consumo do setor atual\n[3] - Finalizar programa")
                opcao = input("Qual sua opção? ")
                while not opcao.isnumeric() or 0 > int(opcao) or int(opcao) > 3:
                    print("Opção invalida, tente novamente!")
                    opcao = input("\nQual sua opção? ")
                opcao = int(opcao)
                if opcao == 1:
                    novaConsulta = True
                    os.system('cls')
                elif opcao == 2:
                    energia = setorAtual // tarifa
                    linhaBase = ["Setor", "Consumo kWh", "Valor R$"]
                    imprimirBonito = PrettyTable(linhaBase)
                    imprimirBonito.add_row([setorNome, energia, f"{setorAtual:.2f}"])
                    imprimirBonito.set_style(MSWORD_FRIENDLY)
                    print(imprimirBonito)
                elif opcao == 3:
                    energia = setorTotal // tarifa
                    ICMS = setorTotal * 0.27
                    PIS = setorTotal * 0.0165
                    COFINS = setorTotal * 0.0761
                    pagamento = setorTotal + ICMS + PIS + COFINS
                    linhaBase = ["Consumo Total kWh", "Valor R$", "ICMS", "PIS", "COFINS", "Pagamento R$"]
                    imprimirBonito = PrettyTable(linhaBase)
                    imprimirBonito.add_row([energia, f"{setorTotal:.2f}", f"{ICMS:.2f}", f"{PIS:.2f}", f"{COFINS:.2f}",
                                            f"{pagamento:.2f}"])
                    print(imprimirBonito)
                    print("\n\nFinalizando...")
                    sys.exit()
    elif int(opcao) == 2:
        print("Finalizando...")
        sys.exit()
    else:
        print("Opção invalida, tente novamente!")