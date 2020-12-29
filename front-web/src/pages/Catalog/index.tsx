import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ProductsResponse } from 'core/types/Product';
import makeRequst from 'core/utils/request';
import ProductCard from './components/ProductCard';
import ProductCardLoarder from './components/Loaders/ProductCardLoarder';
import './styles.scss';

const Catalog = () => {

    //quando a lista de produtos estiver disponível,
    //popular um estado do componente, e listar os produtos dinâmicamente
    const [productsResponse, setProductsResponse] = useState<ProductsResponse>();
    const [isLoading, setIsLoading] = useState(false);

    console.log(productsResponse);

    //quando o componente iniciar, buscar a lista de produtos
    useEffect(() => {
        // limitações
        // muito verboso
        // não tem suporte nativo para ler o progresso de upload de arquivos
        // não tem suporte nativo para enviar query strings
        //fetch('http://localhost:3000/products')
        //.then(response => response.json())
        //.then(response => console.log(response));
        
        //add em utils
        //axios('http://localhost:3000/products')
        //.then(response => console.log(response));

        const params = {
            page: 0,
            linesPerPage: 12
        }
        // iniciar o loader
        setIsLoading(true);
        makeRequst({ url: '/products', params })
            .then(response => setProductsResponse(response.data))
            .finally(() => {
                // finalizar o loader
                setIsLoading(false);
        })
    }, []);

    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de produtos
            </h1>
            <div className="catalog-products">
                {isLoading ? <ProductCardLoarder /> : (
                    productsResponse?.content.map(product => (
                        <Link to={`/products/${product.id}`} key={product.id}>
                            <ProductCard product={product}/>
                        </Link>    
                    ))
                )}
            </div>
        </div>
    )
};

export default Catalog;