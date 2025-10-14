#!/bin/bash

echo "🚀 Starting Wedding Shopping System Frontend..."
echo ""

cd frontend/angular-ecommerce

echo "📦 Setting Node.js environment variables..."
export NODE_OPTIONS=--openssl-legacy-provider

echo "🔧 Starting Angular development server..."
npm start

echo ""
echo "✅ Frontend service started successfully!"
echo "🌐 Access URL: http://localhost:4200"

