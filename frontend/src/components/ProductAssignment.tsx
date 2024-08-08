import React, { useEffect, useState } from 'react';
import axios from 'axios';

interface Product {
    id: number;
    name: string;
    productGroup: string;
    price: number;
}

const ProductAssignment: React.FC = () => {
    const [products, setProducts] = useState<Product[]>([]);
    const [selectedProduct, setSelectedProduct] = useState<number | null>(null);
    const [amount, setAmount] = useState<number>(1);

    useEffect(() => {
        axios.get<Product[]>('http://localhost:8080/products')
            .then(response => {
                console.log('API response:', response.data);
                setProducts(response.data);
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });
    }, []);

    useEffect(() => {
        console.log('Products state:', products);
    }, [products]);

    const increaseAmount = () => {
        setAmount(prevAmount => prevAmount + 1);
    };

    const decreaseAmount = () => {
        setAmount(prevAmount => (prevAmount > 1 ? prevAmount - 1 : 1));
    };

    const assignProduct = () => {
        if (selectedProduct !== null) {
            axios.post('http://localhost:8080/products/add', {
                productId: selectedProduct,
                amount: amount,
            }).then(response => {
                console.log('Product assigned:', response.data);
                // Handle success (e.g., show a success message or update UI)
            }).catch(error => {
                console.error('Error assigning product:', error);
            });
        }
    };

    return (
        <div className="product-assignment">
            <h2>Assign products to vending machine</h2>
            <div className="form-group">
                <label>Product</label>
                <select
                    value={selectedProduct ?? ''}
                    onChange={(e) => setSelectedProduct(Number(e.target.value))}
                >
                    <option value="" disabled>Select product</option>
                    {products.map(product => (
                        <option key={product.id} value={product.id}>{product.name}</option>
                    ))}
                </select>
            </div>
            <div className="form-group">
                <label>Amount</label>
                <div className="amount-controls">
                    <button onClick={increaseAmount}>+</button>
                    <span>{amount}</span>
                    <button onClick={decreaseAmount}>-</button>
                </div>
            </div>
            <div className="form-group">
                <button className="assign-button" onClick={assignProduct}>Assign</button>
            </div>
        </div>
    );
}

export default ProductAssignment;
