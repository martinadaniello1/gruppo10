import sys

if __name__ == "__main__":
    # Verifica che siano stati forniti due parametri da riga di comando
    if len(sys.argv) != 3:
        print("Usage: python script.py param1 param2")
        sys.exit(1)

    # Leggi i parametri dalla riga di comando
    param1 = sys.argv[1]
    param2 = sys.argv[2]

    # Stampa i parametri
    print("Parametro 1:", param1)
    print("Parametro 2:", param2)


    # Restituisci un codice di uscita
    sys.exit()